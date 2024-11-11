package store;

import view.InputView;

public class MembershipManager {
    private static final int LIMIT_DISCOUNT_PRICE = 8000;
    private static final double DISCOUNT_RATE = 0.3;

    public int membershipDiscount(int price) {
        return applyMembershipDiscount(price);
    }

    protected int applyMembershipDiscount(int price) {
        int discountPrice = (int) (price * DISCOUNT_RATE);

        if (!getUserInput()) {
            return price;
        }
        if (discountPrice > LIMIT_DISCOUNT_PRICE) {
            return price - LIMIT_DISCOUNT_PRICE;
        }
        return price - discountPrice;
    }

    protected boolean getUserInput() {
        String answer = InputView.readMembershipDiscount();
        return answer.equalsIgnoreCase("Y");
    }
}
