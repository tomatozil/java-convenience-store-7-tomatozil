package store;

public class Discount {
    private final int discount;
    private final int afterDiscountedPrice;

    public Discount(int discount, int afterDiscountedPrice) {
        this.discount = discount;
        this.afterDiscountedPrice = afterDiscountedPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public int getAfterDiscountedPrice() {
        return afterDiscountedPrice;
    }

}
