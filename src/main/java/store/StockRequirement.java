package store;

public class StockRequirement {
    private final int haveToBuy;
    private final int getFree;

    public StockRequirement(int haveToBuy, int getFree) {
        this.haveToBuy = haveToBuy;
        this.getFree = getFree;
    }

    public int getHaveToBuy() {
        return haveToBuy;
    }

    public int getFree() {
        return getFree;
    }

    public int getTotal() {
        return haveToBuy + getFree;
    }
}
