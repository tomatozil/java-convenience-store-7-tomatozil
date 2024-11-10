package store;

public class DivisionResult {
    private final int normalQuantity;
    private final int eventQuantity;

    public DivisionResult(int normalQuantity, int eventQuantity) {
        this.normalQuantity = normalQuantity;
        this.eventQuantity = eventQuantity;
    }

    public int getNormalQuantity() {
        return normalQuantity;
    }

    public int getEventQuantity() {
        return eventQuantity;
    }
}
