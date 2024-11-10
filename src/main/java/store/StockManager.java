package store;

import view.InputView;

public class StockManager {
    public OrderResult getOrderResult(Item item, int orderQuantity) {
        int modifiedQuantity = getModifiedQuantity(item, orderQuantity);
        return item.calculateBuyAndGetQuantities(modifiedQuantity);
    }

    protected int getModifiedQuantity(Item item, int orderQuantity) {
        if (!item.hasPromotion()) {
            return orderQuantity;
        }

        item.canOrder(orderQuantity);

        if (item.needAdditionalQuantity(orderQuantity)) {
            orderQuantity = applyUserInput(item, orderQuantity);
        }
        return orderQuantity;
    }

    protected int applyUserInput(Item item, int orderQuantity) {
        String answer = InputView.readAdditionalQuantity(item.getName());

        if (answer.equalsIgnoreCase("Y")) {
            orderQuantity += 1;
        }
        return orderQuantity;
    }

}
