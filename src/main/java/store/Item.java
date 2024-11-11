package store;

import java.util.Objects;
import java.util.Optional;

public class Item {
    private final String name;
    private int price;
    private int generalQuantity;
    private int eventQuantity;
    private String promotionType;

    public Item(String name, int price, int generalQuantity, int eventQuantity, String promotionType) {
        this.name = name;
        this.price = price;
        this.generalQuantity = generalQuantity;
        this.eventQuantity = eventQuantity;
        this.promotionType = promotionType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getItemString() {
        StringBuilder sb = new StringBuilder();
        if (promotionType != null) {
            sb.append(getResultEvent()).append("\n");;
        }
        sb.append(getResultprintGeneral()).append("\n");

        return sb.toString();
    }

    private String getResultEvent() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(name).append(" ").append(price).append(" ");
        if (eventQuantity > 0)
            sb.append(eventQuantity).append(" ");
        else
            sb.append("재고 없음");
        sb.append(promotionType);

        return sb.toString();
    }

    private String getResultprintGeneral() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(name).append(" ").append(price).append(" ");
        if (generalQuantity > 0)
            sb.append(generalQuantity);
        else
            sb.append("재고 없음");

        return sb.toString();
    }

    public void updateItemInfo(Item newOne) {
        this.price = newOne.price;
        if (newOne.generalQuantity != -1)
            this.generalQuantity = newOne.generalQuantity;
        if (newOne.eventQuantity != -1)
            this.eventQuantity = newOne.eventQuantity;
        this.promotionType = newOne.promotionType;
    }

    public void canOrder(int orderQuantity) {
        if (orderQuantity > generalQuantity + eventQuantity) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public boolean hasPromotion() {
        return promotionType != null;
    }

    public boolean needAdditionalQuantity(PromotionInventory inventory, int orderQuantity) {
        Optional<Promotion> promotion = inventory.findPromotion(promotionType);
        if (promotion.isEmpty())
            throw new IllegalArgumentException("해당 프로모션이 없습니다.");
        return orderQuantity % promotion.get().buyPlusGet() == promotion.get().getBuy();
    }

    public StockRequirement calculateBuyAndGetQuantities(PromotionInventory inventory, int orderQuantity) {
        Optional<Promotion> promotion = inventory.findPromotion(promotionType);
        if (promotion.isEmpty())
            throw new IllegalArgumentException("해당 프로모션이 없습니다.");

        int total = promotion.get().buyPlusGet();
        int buy = promotion.get().getBuy();
        int get = promotion.get().getGet();

        int bundles = Math.min(orderQuantity / total, eventQuantity / total);
        int haveToBuy = bundles * buy + (orderQuantity - bundles * total);
        int getFree = bundles * get;

        return new StockRequirement(haveToBuy, getFree);
    }
}
