package store;

import view.InputView;

public class MembershipManager {
    private static final int LIMIT_DISCOUNT_PRICE = 8000;
    private static final double DISCOUNT_RATE = 0.3;

    public Discount membershipDiscount(Item item, Order order) {
        String itemName = item.getName();
        int itemPrice = item.getPrice();

        return applyMembershipDiscount(itemName, itemPrice, order);
    }

    protected Discount applyMembershipDiscount(String name, int price, Order order) {
        int afterPromotion = order.deductFreePrice(price);
        int membershipDiscount = (int) (afterPromotion * DISCOUNT_RATE);
        int afterMembership = afterPromotion - membershipDiscount;

        if (!getUserInput(name)) {
            return new Discount(0, afterPromotion);
        }
        if (membershipDiscount > LIMIT_DISCOUNT_PRICE) {
            return new Discount(LIMIT_DISCOUNT_PRICE, afterPromotion - LIMIT_DISCOUNT_PRICE);
        }
        return new Discount(membershipDiscount, afterMembership);
    }

    protected boolean getUserInput(String itemName) {
        String answer = InputView.readMembershipDiscount(itemName);
        if (answer.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }
}
