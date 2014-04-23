package calc.logic;

public class CalculationUtil {
    public static Double calculation(Character token, Double right, Double left) {
        if (isPlus(token)) return left + right;
        if (isMinus(token)) return left - right;
        if (isMultiply(token)) return left * right;
        if (isDivision(token)) return left / right;
        else {
            throw new RuntimeException("Operation is not supported = " + token);
        }
    }

    public static boolean isOperators(char token) {
        return isPriority(token) || isNotPriority(token);
    }

    public static boolean isPriority(char token) {
        return isDivision(token) || isMultiply(token);
    }

    public static boolean isNotPriority(char token) {
        return isPlus(token) || isMinus(token);
    }

    public static boolean isPlus(char token) {
        return token == '+';
    }

    public static boolean isMinus(char token) {
        return token == '-';
    }

    public static boolean isMultiply(char token) {
        return token == '*';
    }

    public static boolean isDivision(char token) {
        return token == '/';
    }

    public static boolean isOpenBracket(char token) {
        return token == '(';
    }

    public static boolean isCloseBracket(char token) {
        return token == ')';
    }
}
