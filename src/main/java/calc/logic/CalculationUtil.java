package calc.logic;

public class CalculationUtil {
    private CalculationUtil() {
    }

    public static String validTypeResult(double doubleResult) {
        int intResult = (int) doubleResult;
        if (intResult == doubleResult) return String.valueOf(intResult);
        else return String.valueOf(doubleResult);
    }
}