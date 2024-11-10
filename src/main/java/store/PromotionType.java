package store;

import java.util.Arrays;
import java.util.function.Function;

public enum PromotionType {
    MD_RECOMMEND("MD추천상품", 1, 1),
    SURPRISE_GIVEAWAY("반짝할인", 1, 1),
    SPARKLE_EVENT("탄산2+1", 2, 1),
    NONE("일반", 0, 0);

    private final String name;
    private final int buy;
    private final int get;

    PromotionType(String name, int buy, int get) {
        this.name = name;
        this.buy = buy;
        this.get = get;
    }

    public DivisionResult getIdealDivision(int requestQuantity) {
        int haveToBuy = (requestQuantity / (this.buy + this.get)) * this.buy;
        int getFree = (requestQuantity / (this.buy + this.get)) * this.get;
        return new DivisionResult(haveToBuy, getFree);
    }

    public int getBuyCount(String name) {
        return Arrays.stream(PromotionType.values())
                .filter(type -> type.name.equals(name))
                .map(type -> type.buy)
                .findAny()
                .orElse(NONE.buy);
    }
}
