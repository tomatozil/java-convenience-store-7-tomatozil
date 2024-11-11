package dto;

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
}
