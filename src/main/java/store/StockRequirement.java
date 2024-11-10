package store;

public class StockRequirement {
    private final int generalStockRequired;
    private final int eventStockRequired;

    public StockRequirement(int generalStockRequired, int eventStockRequired) {
        this.generalStockRequired = generalStockRequired;
        this.eventStockRequired = eventStockRequired;
    }

    public int getGeneralStockRequired() {
        return generalStockRequired;
    }

    public int getEventStockRequired() {
        return eventStockRequired;
    }
}
