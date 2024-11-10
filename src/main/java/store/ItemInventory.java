package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemInventory {
    private final List<Item> itemList;

    public ItemInventory() {
        this.itemList = new ArrayList<>();
    }

    public int productCnt() {
        return itemList.size();
    }

    public void registerItem(Item newOne) {
        itemList.stream()
                .filter(origin -> origin.getName().equals(newOne.getName()))
                .findFirst()
                .ifPresentOrElse(
                        origin -> origin.updateProduct(newOne),
                        () -> itemList.add(newOne)
                );
    }

    public Optional<Item> findItem(String name) {
        return itemList.stream()
                .filter(origin -> origin.getName().equals(name))
                .findFirst();
    }
}
