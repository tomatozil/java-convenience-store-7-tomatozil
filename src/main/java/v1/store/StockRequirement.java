package v1.store;

public class StockRequirement {
    private final int buyFromEventStock;
    private final int buyFromGeneralStock;
    private final int haveToBuy;
    private final int getFree;

    public StockRequirement(int buyFromEventStock, int buyFromGeneralStock, int haveToBuy, int getFree) {
        this.buyFromEventStock = buyFromEventStock;
        this.buyFromGeneralStock = buyFromGeneralStock;
        this.haveToBuy = haveToBuy;
        this.getFree = getFree;
    }

    public boolean isEmpty() {
        return buyFromEventStock == 0
                && buyFromGeneralStock == 0
                && haveToBuy == 0
                && getFree == 0;
    }

    public int getBuyFromEventStock() {
        return buyFromEventStock;
    }

    public int getBuyFromGeneralStock() {
        return buyFromGeneralStock;
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
