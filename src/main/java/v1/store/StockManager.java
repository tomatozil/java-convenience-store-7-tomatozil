package v1.store;

import v1.view.InputView;
import v1.view.OutputView;

public class StockManager {
    private final PromotionInventory promotionInventory;

    public StockManager(PromotionInventory promotionInventory) {
        this.promotionInventory = promotionInventory;
    }

    public StockRequirement getItemStock(Item item, int orderQuantity) {
        int modifiedQuantity = getModifiedQuantity(item, orderQuantity);

        return item.calculateBuyAndGetQuantities(promotionInventory, modifiedQuantity);
    }

    protected int getModifiedQuantity(Item item, int orderQuantity) {
        if (!item.hasPromotion()) {
            return orderQuantity;
        }

        item.canOrder(orderQuantity);

        if (item.needAdditionalQuantity(promotionInventory, orderQuantity)) {
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
