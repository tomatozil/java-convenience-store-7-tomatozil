package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionInventory {
    private final List<Promotion> promotions;

    public PromotionInventory() {
        this.promotions = new ArrayList<>();
    }

    public void registerPromotion(Promotion newOne) {
        promotions.stream()
                .filter(origin -> origin.getName().equals(newOne.getName()))
                .findAny()
                .ifPresentOrElse(
                        origin -> origin.updatePromotionInfo(newOne),
                        () -> promotions.add(newOne)
                );
    }

    public Optional<Promotion> findPromotion(String name) {
        return promotions.stream()
                .filter(type -> type.getName().equals(name))
                .findAny();
    }
}
