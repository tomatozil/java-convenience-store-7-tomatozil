package v1;

import java.util.Map;
import v1.store.ConvenienceStore;
import v1.store.Initializer;
import v1.store.ItemInventory;
import v1.store.MembershipManager;
import v1.store.PromotionInventory;
import v1.store.StockManager;
import v1.store.dto.Receipt;
import v1.view.InputView;
import v1.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        ItemInventory itemInventory = initializer.prepareItems();
        PromotionInventory promotionInventory = initializer.preparePromotions();

        ConvenienceStore convenienceStore = new ConvenienceStore(
                itemInventory,
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
