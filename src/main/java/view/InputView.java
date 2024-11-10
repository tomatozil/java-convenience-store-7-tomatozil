package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Objects;

public class InputView {
    public static String readAdditionalQuantity(String productName) {
        System.out.printf("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", productName);
        String input;
        while (true) {
            input = Console.readLine();
            if ("Y".equalsIgnoreCase(input) || "N".equalsIgnoreCase(input)) {
                return input.toUpperCase();
            }
            System.out.println("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }
}
