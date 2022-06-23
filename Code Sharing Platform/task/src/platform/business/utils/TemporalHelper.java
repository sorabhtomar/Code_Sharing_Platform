package platform.business.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemporalHelper {
    private static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static String getFormattedDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER));
    }

    public static long timeDifferenceInSeconds(LocalDateTime dt1, LocalDateTime dt2) {
        return Duration.between(dt1, dt2).getSeconds();
    }
}