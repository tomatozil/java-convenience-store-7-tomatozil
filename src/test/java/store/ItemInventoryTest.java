package store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemInventoryTest {
    private ItemInventory inventory;

    @BeforeEach
    void 초기_세팅() {
        inventory = new ItemInventory();
    }

    @Test
    void 상품_등록_테스트() {
        inventory.registerItem(new Item("핫도그", 2000, 4, 0, PromotionType.NONE));

        assertThat(inventory.productCnt()).isEqualTo(1);
    }

    @Test
    void 상품_조회_및_수정_테스트() {
        inventory.registerItem(new Item("핫도그", 2000, 4, 0, PromotionType.NONE));
        inventory.registerItem(new Item("핫도그", 2000, 5, 0, PromotionType.NONE));

        assertThatCode(
                () -> inventory.findItem("핫도그").get().canOrder(5))
                .doesNotThrowAnyException();
    }
}