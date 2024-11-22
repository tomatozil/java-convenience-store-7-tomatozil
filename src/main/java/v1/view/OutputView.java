package v1.view;

import java.util.List;
import v1.store.Item;
import v1.store.dto.OrderResult;
import v1.store.dto.Receipt;

public class OutputView {
    public static void printProducts(List<Item> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("안녕하세요. W편의점입니다.\n");
        sb.append("현재 보유하고 있는 상품입니다.\n\n");

        items.forEach(item -> {
            sb.append(item.getItemString());
        });

        System.out.println(sb);
    }

    public static void printReceipt(Receipt receipt) {
        StringBuilder sb = new StringBuilder();

        sb.append("=============W 편의점==============\n");
        sb.append(GPXFormatter.formatLine("상품명", "수량", "금액"));
        sb.append("\n");

        List<OrderResult> orderResults = receipt.getOrderResults();
        orderResults.forEach(orderResult -> {
            sb.append(GPXFormatter.formatLine(orderResult.getName(),
                    String.valueOf(orderResult.getTotalQuantity()),
                    String.format("%,d", orderResult.getTotalPrice())));
            sb.append("\n");
        });

        sb.append("=============증   정==============\n");
        orderResults.stream().filter(orderResult -> orderResult.getFreeQuantity() != 0)
                .forEach(orderResult -> {
                    sb.append(GPXFormatter.formatLine(orderResult.getName(),
                            String.valueOf(orderResult.getFreeQuantity()),
                            ""));
                    sb.append("\n");
                });

        sb.append("================================\n");
        sb.append(GPXFormatter.formatLine("총구매액",
                String.valueOf(receipt.getTotalQuantity()),
                String.valueOf(String.format("%,d", receipt.getTotalPrice()))));
        sb.append("\n");
        sb.append(GPXFormatter.formatLine("행사할인",
                "",
                "-" + String.format("%,d", receipt.getPromotionDiscountPrice())));
        sb.append("\n");
        sb.append(GPXFormatter.formatLine("멤버십할인",
                "",
                "-" + String.format("%,d", receipt.getMembershipDiscountPrice())));
        sb.append("\n");
        sb.append(GPXFormatter.formatLine("내실돈",
                "",
                String.format("%,d", receipt.getFinalPrice())));
        sb.append("\n");

        System.out.println(sb);
    }

    public static class GPXFormatter {
        public static String centerString(int width, String s) {
            if (s.length() >= width) {
                return s.substring(0, width);
            } else {
                int leftPadding = (width - s.length()) / 2;
                int rightPadding = width - s.length() - leftPadding;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < leftPadding; i++) sb.append(" ");
                sb.append(s);
                for (int i = 0; i < rightPadding; i++) sb.append(" ");
                return sb.toString();
            }
        }

        public static String formatLine(String left, String middle, String right) {
            int totalWidth = 30;
            int leftWidth = 10;
            int middleWidth = 10;
            int rightWidth = 10;

            String leftFormatted = String.format("%-" + leftWidth + "s", left);
            String middleFormatted = centerString(middleWidth, middle);
            String rightFormatted = String.format("%" + rightWidth + "s", right);

            return leftFormatted + middleFormatted + rightFormatted;
        }
    }

}
