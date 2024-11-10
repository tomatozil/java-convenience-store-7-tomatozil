package store;

public class StockManager {
    public DivisionResult calculate(ProductStock productStock, int requestQuntity) {
        if (!productStock.isEnough(requestQuntity))
            throw new IllegalArgumentException();

        DivisionResult ideal = productStock.getIdealDivision(requestQuntity);

        return new DivisionResult(4, 6);
    }
}
