package Services;

public class Validator {

    // Simple Email Regex (works for most cases)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Password: at least 8 chars, one uppercase, one lowercase, one digit, one special char
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$";

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
}
