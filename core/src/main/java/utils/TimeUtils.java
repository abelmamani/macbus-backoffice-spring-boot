package utils;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtils {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public static LocalTime parseTime(String timeString) {
        try {
            return LocalTime.parse(timeString, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de tiempo inválido: " + timeString);
        }
    }
    public static String addLocalTimes(LocalTime time1, LocalTime time2) {
        Duration duration1 = Duration.ofSeconds(time1.toSecondOfDay());
        Duration duration2 = Duration.ofSeconds(time2.toSecondOfDay());
        Duration totalDuration = duration1.plus(duration2);
        long totalMinutes = totalDuration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
