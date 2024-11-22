package store;

import java.util.Map;
import store.dto.Receipt;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        ItemInventory itemInventory = initializer.prepareItems();
        PromotionInventory promotionInventory = initializer.preparePromotions();

        ConvenienceStore convenienceStore = new ConvenienceStore(
                itemInventory,
                promotionInventory,
                new StockManager(promotionInventory),
                new MembershipManager()
        );

        while (true) {
            try {
                itemInventory.printItems();

                Map<String, Integer> orderList = InputView.readOrderList();

                Receipt receipt = convenienceStore.order(orderList);

                OutputView.printReceipt(receipt);

                String close = InputView.readOrderAgain();
                if (close.equalsIgnoreCase("Y"))
                    continue;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
