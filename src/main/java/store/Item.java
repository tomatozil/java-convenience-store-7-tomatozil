package store;

import java.util.Objects;

public class Item {
    private final String name;
    private int price;
    private int generalQuantity;
    private int eventQuantity;
    private PromotionType promotionType;

    public Item(String name, int price, int generalQuantity, int eventQuantity, PromotionType promotionType) {
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

    public void updateProduct(Item newOne) {
        if (this.equals(newOne))
            return ;
        this.price = newOne.price;
        this.generalQuantity = newOne.generalQuantity;
        this.eventQuantity = newOne.eventQuantity;
        this.promotionType = newOne.promotionType;
    }

    public void canOrder(int orderQuantity) {
        if (orderQuantity > generalQuantity + eventQuantity) {
            throw new IllegalArgumentException();
        }
    }

    public boolean hasPromotion() {
        return promotionType != PromotionType.NONE;
    }

    public boolean needAdditionalQuantity(int orderQuantity) {
        return orderQuantity % promotionType.buyPlusGet() == promotionType.getBuy();
    }

    public Order calculateBuyAndGetQuantities(int orderQuantity) {
        int total = promotionType.buyPlusGet();
        int buy = promotionType.getBuy();
        int get = promotionType.getGet();

        int bundles = Math.min(orderQuantity / total, eventQuantity / total);
        int haveToBuy = bundles * buy + (orderQuantity - bundles * total);
        int getFree = bundles * get;

        return new Order(haveToBuy, getFree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generalQuantity, eventQuantity, promotionType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item item = (Item) obj;
        return generalQuantity == item.generalQuantity
                && eventQuantity == item.eventQuantity
                && promotionType.equals(item.promotionType);
    }
}
