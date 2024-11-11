package view;

import java.util.List;
import store.Item;

public class OutputView {
    public static void printProducts(List<Item> items) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");

        StringBuilder sb = new StringBuilder();
        items.forEach(item -> {
            sb.append(item.getItemString());
        });

        System.out.println(sb);
    }
}
