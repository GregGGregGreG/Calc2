package calc.logic;


public class CalculationUtil {
    private CalculationUtil() {
    }

    public static String validTypeResult(double doubleResult) {
        int intResult = (int) doubleResult;
        if (intResult == doubleResult) return String.valueOf(intResult);
        else return String.valueOf(doubleResult);
    }

    public static Double calculation(Character token, Double right, Double left) {
        if (isPlus(token)) return left + right;
        if (isMinus(token)) return left - right;
        if (isMultiply(token)) return left * right;
        if (isPow(token)) return Math.pow(left, right);
        else return left / right;
    }

    public static boolean isOperators(char token) {
        return isPriority(token) || isNotPriority(token);
    }

    public static boolean isNotPriority(char token) {
        return isPlus(token) || isMinus(token);
    }

    public static boolean isPriority(char token) {
        return isDivision(token) || isMultiply(token) || isBinaryOperation(token);
    }

    public static boolean isBinaryOperation(char token) {
        return isPow(token);
    }


    public static boolean isPlus(char token) {
        return token == '+';
    }

    public static boolean isMinus(char token) {
        return token == '-';
    }

    public static boolean isMultiply(char token) {
        return token == '*' || token == '×';
    }

    public static boolean isDivision(char token) {
        return token == '/' || token == '÷';
    }

    public static boolean isOpenBracket(char token) {
        return token == '(';
    }

    public static boolean isCloseBracket(char token) {
        return token == ')';
    }

    public static boolean isPow(char token) {
        return token == '^';
    }
}