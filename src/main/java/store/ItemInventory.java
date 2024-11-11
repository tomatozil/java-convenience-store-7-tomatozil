package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import view.OutputView;

public class ItemInventory {
    private final List<Item> items;


    public ItemInventory() {
        this.items = new ArrayList<>();
    }

    public int productCnt() {
        return items.size();
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
