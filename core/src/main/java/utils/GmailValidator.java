package utils;

public class GmailValidator {
    public static boolean isValidGmailAddress(String email) {
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return email.matches(gmailRegex);
    }
}
