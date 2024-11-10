package store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StockManagerTest {
    @Test
    void 수량_측정_테스트() {
        ProductStock productStock = new ProductStock(new Product("콜라"), 10, 7, PromotionType.SPARKLE_EVENT);
        StockManager manager = new StockManager();

        assertThat(manager.calculate(productStock, 10).getEventQuantity()).isEqualTo(6);
    }
}
