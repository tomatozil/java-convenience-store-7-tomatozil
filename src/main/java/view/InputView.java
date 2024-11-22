package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class InputView {
    public static Map<String, Integer> readOrderList() {
        Map<String, Integer> productMap;
        while (true) {
            try {
                System.out.printf("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])\n");
                String input = Console.readLine();
                productMap = new HashMap<>();

                String[] items = input.split(",");

                for (String item : items) {
                    String cleanedItem = item.replaceAll("[\\[\\]]", "");
                    String[] parts = cleanedItem.split("-");

                    if (parts.length != 2 || !isNumeric(parts[1])) {
                        throw new IllegalArgumentException("잘못된 형식의 입력입니다: " + item);
                    }

                    String productName = parts[0];
                    int quantity = Integer.parseInt(parts[1]);

                    productMap.put(productName, quantity);
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        return productMap;
    }

    public static String readOrderAgain() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return getInputYOrN();
    }

    public static String readAdditionalQuantity(String itemName) {
        System.out.printf("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", itemName);
        return getInputYOrN();
    }

    public static String readMembershipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        return getInputYOrN();
    }

    private static String getInputYOrN() {
        String input;
        while (true) {
            input = Console.readLine();
            if ("Y".equalsIgnoreCase(input) || "N".equalsIgnoreCase(input)) {
                return input.toUpperCase();
            }
            System.out.println("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
