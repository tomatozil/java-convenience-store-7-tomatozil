package store;

public class ConvenienceStore {
    private final ItemInventory inventory;
    private final StockManager stockManager;
    private final MembershipManager membershipManager;

    public ConvenienceStore(ItemInventory inventory, StockManager stockManager, MembershipManager membershipManager) {
        this.inventory = inventory;
        this.stockManager = stockManager;
        this.membershipManager = membershipManager;
    }

    public void order(String name, int quantity) {
        Item item = inventory.findItem(name).orElseThrow(IllegalArgumentException::new);

        Order order = stockManager.order(item, quantity);

        Discount discount = membershipManager.membershipDiscount(item, order);
    }
}
