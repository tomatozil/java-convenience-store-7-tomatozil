package store;

public class ProductInventory {
    private final String name;
    private final int generalQuantity;
    private final int eventQuantity;
    private final PromotionType promotionType;

    public ProductInventory(String name, int generalQuantity) {
        this.name = name;
        this.generalQuantity = generalQuantity;
        this.eventQuantity = 0;
        this.promotionType = PromotionType.NONE;
    }

    public ProductInventory(String name, int generalQuantity, int eventQuantity, PromotionType promotionType) {
        this.name = name;
        this.generalQuantity = generalQuantity;
        this.eventQuantity = eventQuantity;
        this.promotionType = promotionType;
    }

    public String getName() {
        return name;
    }

    public void canOrder(int orderQuantity) {
        if (orderQuantity > generalQuantity + eventQuantity)
            throw new IllegalArgumentException();
    }

    public boolean hasPromotion() {
        return promotionType != PromotionType.NONE;
    }

    public boolean needAdditionalQuantity(int orderQuantity) {
        return orderQuantity % promotionType.buyPlusGet() == promotionType.getBuy();
    }

    public OrderResult calculateBuyAndGetQuantities(int orderQuantity) {
        int total = promotionType.buyPlusGet();
        int buy = promotionType.getBuy();
        int get = promotionType.getGet();

        int cnt = eventQuantity / total;
        for(int i = cnt; i > 0; i--) {
            if (orderQuantity >= i * total) {
                int haveToBuy = i * buy + (orderQuantity - i * total);
                int getFree = i * get;
                return new OrderResult(haveToBuy, getFree);
            }
        }
        return new OrderResult(orderQuantity, 0);
    }
}
