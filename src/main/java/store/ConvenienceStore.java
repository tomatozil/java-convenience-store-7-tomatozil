package store;

import dto.OrderResult;
import dto.Receipt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvenienceStore {
    private final ItemInventory inventory;
    private final StockManager stockManager;
    private final MembershipManager membershipManager;

    public ConvenienceStore(ItemInventory inventory, StockManager stockManager, MembershipManager membershipManager) {
        this.inventory = inventory;
        this.stockManager = stockManager;
        this.membershipManager = membershipManager;
    }

    public Receipt order(Map<String, Integer> orderList) {
        Map<Item, StockRequirement> itemStockTable = createItemStockTable(orderList);

        int total = calculateTotalPrice(itemStockTable);
        int afterPromotion = calculateAfterPromotionPrice(itemStockTable);
        int afterMembership = membershipManager.membershipDiscount(afterPromotion);

        List<OrderResult> orderResults = getAllOrderResults(itemStockTable);
        int promotionDiscountPrice = total - afterPromotion;
        int membershipDiscountPrice = afterPromotion - afterMembership;
        int finalPrice = afterMembership;

        return new Receipt(orderResults, total, promotionDiscountPrice, membershipDiscountPrice, finalPrice);
    }

    protected Map<Item, StockRequirement> createItemStockTable(Map<String, Integer> orderList) {
        Map<Item, StockRequirement> itemStockTable = new HashMap<>();

        orderList.forEach((itemName, quantity) -> {
            Item item = inventory.findItem(itemName)
                    .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemName));
            StockRequirement stockRequirement = stockManager.getItemStock(item, quantity);

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
        List <OrderResult> orderResults = new ArrayList<>();

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
