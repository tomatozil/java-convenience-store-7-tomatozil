package store;

public class OrderResult {
    private final int haveToBuy;
    private final int getFree;

    public OrderResult(int haveToBuy, int getFree) {
        this.haveToBuy = haveToBuy;
        this.getFree = getFree;
    }

    public int getHaveToBuy() {
        return haveToBuy;
    }

    public int getFree() {
        return getFree;
    }
}
