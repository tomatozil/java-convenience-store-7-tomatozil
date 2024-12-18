package store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import v1.store.Item;
import v1.store.PromotionInventory;
import v1.store.StockManager;
import v1.store.StockRequirement;

public class StockManagerTest {
    private StockManager manager;

    @BeforeEach
    void 초기_세팅() {
        manager = new StockManager(new PromotionInventory());
    }

    @Test
    void 원플러스원_이벤트_재고_충분할_때_수량_측정_테스트() {
        manager = new StockManager(new PromotionInventory()) {
            @Override
            protected int applyUserInput(Item inventory, int orderQuantity) {
                return orderQuantity + 1;
            }
        };
        Item item = new Item("에너지바", 1500, 0, 5, "MD추천상품");
        StockRequirement stockRequirement = manager.getItemStock(item, 1);
        assertThat(stockRequirement.getFree()).isEqualTo(1);
    }

    @Test
    void 투플러스원_이벤트_재고_충분할_때_수량_측정_테스트() {
        Item item = new Item("콜라",1000, 10, 7, "탄산2+1");
        StockRequirement stockRequirement = manager.getItemStock(item, 10);
        assertThat(stockRequirement.getHaveToBuy()).isEqualTo(8);
    }

    @Test
    void 투플러스원_이벤트_재고_없을_때_수량_측정_테스트() {
        Item item = new Item("콜라", 1000, 10, 0, "탄산2+1");
        StockRequirement stockRequirement = manager.getItemStock(item, 10);
        assertThat(stockRequirement.getHaveToBuy()).isEqualTo(10);
    }
}
