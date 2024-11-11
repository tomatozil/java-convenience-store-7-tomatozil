package store;

public class Order {
    private final int haveToBuy;
    private final int getFree;

    public Order(int haveToBuy, int getFree) {
        this.haveToBuy = haveToBuy;
        this.getFree = getFree;
    }

    public int getHaveToBuy() {
        return haveToBuy;
    }

    public int getFree() {
        return getFree;
    }

    public int deductFreePrice(int itemPrice) {
        if (getFree == 0)
            return 0;
        int total = itemPrice * (haveToBuy + getFree);
        int freePrice = itemPrice * getFree;
        return total - freePrice;
    }
}
