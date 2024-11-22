package v1.store;

import java.util.Optional;
import v1.view.InputView;

public class Item {
    private final String name;
    private int price;
    private int generalQuantity;
    private int eventQuantity;
    private String promotionType;

    public Item(String name, int price, int generalQuantity, int eventQuantity, String promotionType) {
        this.name = name;
        this.price = price;
        this.generalQuantity = generalQuantity;
        this.eventQuantity = eventQuantity;
        this.promotionType = promotionType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getGeneralQuantity() {
        return generalQuantity;
    }

    public int getEventQuantity() {
        return eventQuantity;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public String getItemString() {
        StringBuilder sb = new StringBuilder();
        if (promotionType != null) {
            sb.append(getResultEvent()).append("\n");;
        }
        sb.append(getResultprintGeneral()).append("\n");

        return sb.toString();
    }

    private String getResultEvent() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(name).append(" ").append(String.format("%,d", price)).append("원 ");
        if (eventQuantity > 0)
            sb.append(eventQuantity).append("개 ");
        else
            sb.append("재고 없음");
        sb.append(promotionType);

        return sb.toString();
    }

    private String getResultprintGeneral() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(name).append(" ").append(String.format("%,d", price)).append("원 ");
        if (generalQuantity > 0)
            sb.append(generalQuantity).append("개 ");
        else
            sb.append("재고 없음");

        return sb.toString();
    }

    public void updateItemInfo(Item newOne) {
        if (this.promotionType != null && newOne.promotionType == null) {
            this.generalQuantity = newOne.generalQuantity;
            return;
        }

        if (this.promotionType == null && newOne.promotionType != null) {
            this.eventQuantity = newOne.eventQuantity;
            this.promotionType = newOne.promotionType;
            return;
        }

        this.price = newOne.price;
        this.generalQuantity = newOne.generalQuantity;
        this.eventQuantity = newOne.eventQuantity;
        this.promotionType = newOne.promotionType;
    }

    public void canOrder(int orderQuantity) {
        if (orderQuantity > generalQuantity + eventQuantity) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public boolean hasPromotion() {
        return promotionType != null;
    }

    public boolean needAdditionalQuantity(PromotionInventory inventory, int orderQuantity) {
        Optional<Promotion> promotion = inventory.findPromotion(promotionType);
        if (promotion.isEmpty())
            throw new IllegalArgumentException("해당 프로모션이 없습니다.");
        return orderQuantity % promotion.get().buyPlusGet() == promotion.get().getBuy();
    }

    public StockRequirement calculateBuyAndGetQuantities(PromotionInventory inventory, int orderQuantity) {
        if (promotionType == null)
            return new StockRequirement(0, orderQuantity, orderQuantity, 0);

        Optional<Promotion> promotion = inventory.findPromotion(promotionType);
        if (promotion.isEmpty())
            throw new IllegalArgumentException("해당 프로모션이 없습니다.");

        if (!promotion.get().isActive())
            return new StockRequirement(0, orderQuantity, orderQuantity, 0);

        int total = promotion.get().buyPlusGet();
        int buy = promotion.get().getBuy();
        int get = promotion.get().getGet();

        int bundles = Math.min(orderQuantity / total, eventQuantity / total);
        int buyFromEventStock = eventQuantity;
        int buyFromGeneralStock = orderQuantity - eventQuantity;
        String answer = InputView.readAgreeWithNotApplyingPromotion(orderQuantity - bundles * total);
        if (answer.equalsIgnoreCase("N"))
            return new StockRequirement(0,0,0,0);
        int haveToBuy = bundles * buy + (orderQuantity - bundles * total);
        int getFree = bundles * get;

        return new StockRequirement(buyFromEventStock, buyFromGeneralStock, haveToBuy, getFree);
    }
}
