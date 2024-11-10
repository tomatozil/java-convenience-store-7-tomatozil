package store;

public enum PromotionType {
    MD_RECOMMEND("MD추천상품", 1, 1),
    SURPRISE_GIVEAWAY("반짝할인", 1, 1),
    SPARKLE_EVENT("탄산2+1", 2, 1),
    NONE("일반", 1, 0);

    private final String name;
    private final int buy;
    private final int get;

    PromotionType(String name, int buy, int get) {
        this.name = name;
        this.buy = buy;
        this.get = get;
    }

    public int buyPlusGet() {
        return buy + get;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }
}
