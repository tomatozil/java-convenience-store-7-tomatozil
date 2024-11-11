package store;

import file.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Initializer {
    private final FileReader fileReader;

    public Initializer() {
        this.fileReader = new FileReader();
    }

    public PromotionInventory preparePromotions() {
        PromotionInventory inventory = new PromotionInventory();

        String promotionText = fileReader.readFile("promotions.md");
        List<String> promotionLines = Arrays.asList(promotionText.split("\n"))
                .subList(1, promotionText.split("\n").length);
        registerPromotion(inventory, promotionLines);

        return inventory;
    }

    public ItemInventory prepareItems() {
        ItemInventory itemInventory = new ItemInventory();

        String productsText = fileReader.readFile("products.md");
        List<String> productLines = Arrays.asList(productsText.split("\n"))
                .subList(1, productsText.split("\n").length);
        registerItems(itemInventory, productLines);

        return itemInventory;
    }

    public void registerPromotion(PromotionInventory inventory, List<String> lines) {
        lines.forEach(line -> {
            Promotion promotion = makePromotionFromText(line.split(","));
            inventory.registerPromotion(promotion);
        });
    }

    public void registerItems(ItemInventory inventory, List<String> lines) {
        lines.forEach(line -> {
            Item item = makeItemFromText(line.split(","));

            inventory.registerItem(item);
        });
    }

    protected Promotion makePromotionFromText(String[] elements) {
        String name = elements[0];
        int buy = Integer.parseInt(elements[1]);
        int get = Integer.parseInt(elements[2]);
        String startDate = elements[3];
        String endDate = elements[4];

        return new Promotion(name, buy, get, startDate, endDate);
    }

    protected Item makeItemFromText(String[] elements) {
        String name = elements[0];
        int price = Integer.parseInt(elements[1]);
        int quantity = Integer.parseInt(elements[2]);

        String promotionType;
        int generalQuantity = -1;
        int eventQuantity = -1;

        if ("null".equals(elements[3])) {
            promotionType = null;
            generalQuantity = quantity;
        } else {
            promotionType = elements[3];
            eventQuantity = quantity;
        }

        return new Item(name, price, generalQuantity, eventQuantity, promotionType);
    }
}
