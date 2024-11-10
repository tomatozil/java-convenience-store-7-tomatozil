package store;

import view.InputView;

public class StockManager {
    public OrderResult getOrderResult(ProductInventory productInventory, int orderQuantity) {
        // not a promotional product
        if (!productInventory.hasPromotion())
            return new OrderResult(orderQuantity, 0);

        // total quantity is insufficient
        productInventory.canOrder(orderQuantity);

        // have to bring more
        if (productInventory.needAdditionalQuantity(orderQuantity)) {
            String answer = InputView.readAdditionalQuantity(productInventory.getName());
            if (answer.equalsIgnoreCase("Y"))
                orderQuantity += 1;
        }

        return productInventory.calculateBuyAndGetQuantities(orderQuantity);
    }
}
