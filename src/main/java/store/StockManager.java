package store;

import view.InputView;

public class StockManager {
    public OrderResult getOrderResult(ProductInventory inventory, int orderQuantity) {
        int modifiedQuantity = getModifiedQuantity(inventory, orderQuantity);
        return inventory.calculateBuyAndGetQuantities(modifiedQuantity);
    }

    protected int getModifiedQuantity(ProductInventory inventory, int orderQuantity) {
        if (!inventory.hasPromotion()) {
            return orderQuantity;
        }

        inventory.canOrder(orderQuantity);

        if (inventory.needAdditionalQuantity(orderQuantity)) {
            orderQuantity = applyUserInput(inventory, orderQuantity);
        }
        return orderQuantity;
    }

    protected int applyUserInput(ProductInventory inventory, int orderQuantity) {
        String answer = InputView.readAdditionalQuantity(inventory.getName());

        if (answer.equalsIgnoreCase("Y")) {
            orderQuantity += 1;
        }
        return orderQuantity;
    }

}
