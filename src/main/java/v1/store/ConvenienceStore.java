package v1.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import v1.store.dto.OrderResult;
import v1.store.dto.Receipt;

public class ConvenienceStore {
    private final ItemInventory itemInventory;
    private final StockManager stockManager;
    private final MembershipManager membershipManager;

    public ConvenienceStore(
            ItemInventory itemInventory,
            StockManager stockManager,
            MembershipManager membershipManager
    ) {
        this.itemInventory = itemInventory;
        this.stockManager = stockManager;
        this.membershipManager = membershipManager;
    }

    public Receipt order(Map<String, Integer> orderList) {
        Map<Item, StockRequirement> itemStockTable = createItemStockTable(orderList);

        itemStockTable.forEach(itemInventory::reductItemStock);

        int totalQuantity = itemStockTable.values().stream()
                .mapToInt(StockRequirement::getTotal)
                .sum();
        int totalPrice = calculateTotalPrice(itemStockTable);
        int afterPromotion = calculateAfterPromotionPrice(itemStockTable);
        int afterMembership = membershipManager.membershipDiscount(afterPromotion);

        List<OrderResult> orderResults = getAllOrderResults(itemStockTable);
        int promotionDiscountPrice = totalPrice - afterPromotion;
        int membershipDiscountPrice = afterPromotion - afterMembership;
        int finalPrice = afterMembership;

        return new Receipt(orderResults,
                totalQuantity,
                totalPrice,
                promotionDiscountPrice,
                membershipDiscountPrice,
                finalPrice);
    }

    protected Map<Item, StockRequirement> createItemStockTable(Map<String, Integer> orderList) {
        Map<Item, StockRequirement> itemStockTable = new HashMap<>();

        orderList.forEach((itemName, quantity) -> {
            Item item = itemInventory.findItem(itemName)
                    .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));
            StockRequirement stockRequirement = stockManager.getItemStock(item, quantity);

            if (!stockRequirement.isEmpty())
                itemStockTable.put(item, stockRequirement);
        });
        return itemStockTable;
    }

    protected int calculateTotalPrice(Map<Item, StockRequirement> itemStockTable) {
        return itemStockTable.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue().getTotal())
                .sum();
    }

    protected int calculateAfterPromotionPrice(Map<Item, StockRequirement> itemStockTable) {
        return itemStockTable.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue().getHaveToBuy())
                .sum();
    }

    protected List<OrderResult> getAllOrderResults(Map<Item, StockRequirement> itemStockTable) {
        List<OrderResult> orderResults = new ArrayList<>();

        itemStockTable.forEach((item, stockRequirement) -> {
            String name = item.getName();
            int totalQuantity = stockRequirement.getTotal();
            int totalPrice = item.getPrice() * totalQuantity;
            int freeQuantity = stockRequirement.getFree();

            orderResults.add(new OrderResult(name, totalQuantity, totalPrice, freeQuantity));
        });

        return orderResults;
    }
}
