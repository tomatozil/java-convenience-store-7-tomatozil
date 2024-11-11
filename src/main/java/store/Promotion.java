package store;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.format.DateTimeFormatter;

public class Promotion {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String name;
    private int buy;
    private int get;
    private String startDate;
    private String endDate;

    public Promotion(String name, int buy, int get, String startDate, String endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int buyPlusGet() {
        return buy + get;
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public void updatePromotionInfo(Promotion newOne) {
        this.buy = newOne.buy;
        this.get = newOne.get;
        this.startDate = newOne.startDate;
        this.endDate = newOne.endDate;
    }

    public boolean isActive() {
        String currentDate = DateTimes.now().format(DATE_FORMATTER);
        return currentDate.compareTo(startDate) >= 0
                && currentDate.compareTo(endDate) <= 0;
    }
}
