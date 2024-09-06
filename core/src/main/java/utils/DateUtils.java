package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter DATE_FORMATTER =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static boolean isValidDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return false;
        }
        try {
            LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static LocalDate getLocalDate(String date) {
        if(!isValidDate(date))
            throw new IllegalArgumentException("Fecha no válida: " + date);
        return  LocalDate.parse(date, DATE_FORMATTER);
    }
}
