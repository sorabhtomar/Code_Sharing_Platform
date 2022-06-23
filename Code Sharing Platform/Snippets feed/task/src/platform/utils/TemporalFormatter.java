package platform.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemporalFormatter {
    private static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static String getFormattedDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER));
    }
}