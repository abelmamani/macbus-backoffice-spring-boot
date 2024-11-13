package utils;

public class ValidatorUtils {
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return password.matches(passwordRegex);
    }
    public static boolean isValidHexColor(String color) {
        String hexColorRegex = "^#([A-Fa-f0-9]{6})$";
        return color.matches(hexColorRegex);
    }

    public static boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }

    public static boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= -180 && longitude <= 180;
    }
}
