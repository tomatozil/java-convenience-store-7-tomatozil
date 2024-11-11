package store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MembershipManagerTest {
    private MembershipManager manager;
    private Item item;
    private StockRequirement stockRequirement;

    @BeforeEach
    void 초기_세팅() {
        item = new Item("에너지바", 1500, 0, 5, PromotionType.MD_RECOMMEND);
        stockRequirement = new StockRequirement(1, 1);
    }

    @Test
    void 멤버십_할인_테스트() {
        manager = new MembershipManager() {
            @Override
            protected boolean getUserInput() {
                return true;
            }
        };

        assertThat(manager.membershipDiscount(1500))
                .isEqualTo(1500 - (int)(1500 * 0.3));
    }
}