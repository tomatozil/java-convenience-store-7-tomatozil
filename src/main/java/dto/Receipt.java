package dto;

import java.util.List;

public class Receipt {
    List<OrderResult> orderResults;
    int totalPrice;
    int promotionDiscountPrice;
    int membershipDiscountPrice;
    int finalPrice;

    public Receipt(List<OrderResult> orderResults, int totalPrice, int promotionDiscountPrice,
                   int membershipDiscountPrice, int finalPrice) {
        this.orderResults = orderResults;
        this.totalPrice = totalPrice;
        this.promotionDiscountPrice = promotionDiscountPrice;
        this.membershipDiscountPrice = membershipDiscountPrice;
        this.finalPrice = finalPrice;
    }
}
