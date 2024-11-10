package store;

public class ProductStock {
    private final Product product;
    private final int normalQuantity;
    private final int eventQuantity;
    private final PromotionType promotionType;

    public ProductStock(Product product, int normalQuantity, int eventQuantity, PromotionType promotionType) {
        this.product = product;
        this.normalQuantity = normalQuantity;
        this.eventQuantity = eventQuantity;
        this.promotionType = promotionType;
    }

    public boolean isEnough(int requestQuantity) {
        return requestQuantity < normalQuantity + eventQuantity;
    }

    public DivisionResult getIdealDivision(int requestQuantity) {
        return promotionType.getIdealDivision(requestQuantity);
    }
}
