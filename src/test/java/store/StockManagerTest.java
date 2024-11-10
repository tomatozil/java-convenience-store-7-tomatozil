package store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockManagerTest {
    private StockManager manager;

    @BeforeEach
    void setUp() {
        manager = new StockManager();
    }

    @Test
    void 원플러스원_이벤트_재고_충분할_때_수량_측정_테스트() {
        manager = new StockManager() {
            @Override
            protected int applyUserInput(ProductInventory inventory, int orderQuantity) {
                return orderQuantity + 1;
            }
        };
        ProductInventory productInventory = new ProductInventory("에너지바", 0, 5, PromotionType.MD_RECOMMEND);
        OrderResult orderResult = manager.getOrderResult(productInventory, 1);
        assertThat(orderResult.getFree()).isEqualTo(1);
    }

    @Test
    void 투플러스원_이벤트_재고_충분할_때_수량_측정_테스트() {
        ProductInventory productInventory = new ProductInventory("콜라", 10, 7, PromotionType.SPARKLE_EVENT);
        OrderResult orderResult = manager.getOrderResult(productInventory, 10);
        assertThat(orderResult.getHaveToBuy()).isEqualTo(8);
    }

    @Test
    void 투플러스원_이벤트_재고_없을_때_수량_측정_테스트() {
        ProductInventory productInventory = new ProductInventory("콜라", 10, 0, PromotionType.SPARKLE_EVENT);
        OrderResult orderResult = manager.getOrderResult(productInventory, 10);
        assertThat(orderResult.getHaveToBuy()).isEqualTo(10);
    }
}
