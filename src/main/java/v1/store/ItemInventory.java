package v1.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import v1.view.OutputView;

public class ItemInventory {
    private final List<Item> items;


    public ItemInventory() {
        this.items = new ArrayList<>();
    }

    public int productCnt() {
        return items.size();
    }

    public void reductItemStock(Item item, StockRequirement stockRequirement) {
        int generalQuantity = item.getGeneralQuantity();
        int eventQuantity = item.getEventQuantity();

        if (stockRequirement.getFree() != 0) {
            eventQuantity -= stockRequirement.getTotal();
        } else {
            generalQuantity -= stockRequirement.getTotal();
        }
        item.updateItemInfo(
                new Item(item.getName(), item.getPrice(), generalQuantity, eventQuantity, item.getPromotionType()));
    }

    public void registerItem(Item newOne) {
        items.stream()
                .filter(origin -> origin.getName().equals(newOne.getName()))
                .findAny()
                .ifPresentOrElse(
                        origin -> origin.updateItemInfo(newOne),
                        () -> items.add(newOne)
                );
    }

    public Optional<Item> findItem(String name) {
        return items.stream()
                .filter(origin -> origin.getName().equals(name))
                .findAny();
    }

    public void printItems() {
        OutputView.printProducts(items);
    }
}
