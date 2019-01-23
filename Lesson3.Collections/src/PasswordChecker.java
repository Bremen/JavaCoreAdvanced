import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {
    private static Matcher matcher;
    private static final int SIZE_MIN = 8;

    private static final String LENGTH_PATTERN = String.format("\\S{%d,}",SIZE_MIN);
    private static final String NUMBERS_GROUP = "(?=.*[0-9])";
    private static final String UP_CASE_LETTERS_GROUP = "(?=.*[A-Z])";
    private static final String DOWN_CASE_LETTERS_GROUP = "(?=.*[a-z])";
    private static final String SPECIAL_SYMBOLS_GROUP = "(?=.*[!-/:-@\\[-`{-~])";


//    private static final String PASSWORD_COMMON_PATTERN = "^(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])(?=.*[a-z])(?=.*[A-Z])\\S{8,}$";
    private static final String PASSWORD_COMMON_PATTERN = "^"
                                                            + NUMBERS_GROUP
                                                            + UP_CASE_LETTERS_GROUP
                                                            + DOWN_CASE_LETTERS_GROUP
                                                            + SPECIAL_SYMBOLS_GROUP
                                                            + LENGTH_PATTERN
                                                            + "$";

    private static Pattern pattern = Pattern.compile(PASSWORD_COMMON_PATTERN);

    private static boolean validate(final String password){
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static boolean validate(final String password, final String... groups){
        StringBuilder partialPattern = new StringBuilder("^");

        for (String part : groups) {
            partialPattern.append(part);
        }

        partialPattern.append("$");

        pattern = Pattern.compile(partialPattern.toString());
        matcher = pattern.matcher(password);
        return !matcher.matches();
    }

    public static void main(String[] args) {
        boolean isPasswordCorrected;
        System.out.println("Введите пароль:");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        isPasswordCorrected = validate(password);
        System.out.println(isPasswordCorrected);

        if(!isPasswordCorrected){
            reportPasswordProblem(password);
        }
    }

    private static void reportPasswordProblem(String password) {
        String space_group = "\\S{1,}";

        if (password.length() < SIZE_MIN){
            System.out.printf("Ваш пароль короче %d символов.\n", SIZE_MIN);
        }
        if(validate(password, space_group)){
            System.out.println("Ваш пароль содержит пробельные символы.");
        }
        if(validate(password, NUMBERS_GROUP, space_group)){
            System.out.println("Ваш пароль не содержит цифр, хотя бы одну.");
        }
        if(validate(password, UP_CASE_LETTERS_GROUP, space_group)){
            System.out.println("Ваш пароль не содержит заглавных латинских букв, хотя бы одну.");
        }
        if(validate(password, DOWN_CASE_LETTERS_GROUP, space_group)){
            System.out.println("Ваш пароль не содержит строчных латинских букв, хотя бы одну.");
        }
        if(validate(password, SPECIAL_SYMBOLS_GROUP, space_group)){
            System.out.println("Ваш пароль не содержит специальных символов, хотя бы один.");
        }
    }
}
