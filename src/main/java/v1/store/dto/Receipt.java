package v1.store.dto;

import java.util.List;

public class Receipt {
    List<OrderResult> orderResults;
    int totalQuantity;
    int totalPrice;
    int promotionDiscountPrice;
    int membershipDiscountPrice;
    int finalPrice;

    public Receipt(List<OrderResult> orderResults, int totalQuantity, int totalPrice, int promotionDiscountPrice,
                   int membershipDiscountPrice, int finalPrice) {
        this.orderResults = orderResults;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.promotionDiscountPrice = promotionDiscountPrice;
        this.membershipDiscountPrice = membershipDiscountPrice;
        this.finalPrice = finalPrice;
    }

    public List<OrderResult> getOrderResults() {
        return orderResults;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getPromotionDiscountPrice() {
        return promotionDiscountPrice;
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }
}
