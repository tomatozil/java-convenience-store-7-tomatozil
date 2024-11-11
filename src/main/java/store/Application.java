package store;

import java.util.Map;
import store.dto.Receipt;
import view.InputView;

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

        itemInventory.printItems();

        Map<String, Integer> orderList;
        while (true) {
            try {
                orderList = InputView.readOrderList();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        Receipt receipt = convenienceStore.order(orderList);
    }
}
