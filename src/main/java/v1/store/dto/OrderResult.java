package v1.store.dto;

public class OrderResult {
    String name;
    int totalQuantity;
    int totalPrice;
    int freeQuantity;

    public OrderResult(String name, int totalQuantity, int totalPrice, int freeQuantity) {
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.freeQuantity = freeQuantity;
    }

    public String getName() {
        return name;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }
}
