package store;

public class ConvenienceStore {
    private final ItemInventory inventory;
    private final StockManager stockManager;

    public ConvenienceStore(ItemInventory inventory, StockManager stockManager) {
        this.inventory = inventory;
        this.stockManager = stockManager;
    }
}
