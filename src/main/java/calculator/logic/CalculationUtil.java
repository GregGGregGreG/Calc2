package calculator.logic;

public class CalculationUtil {

    public static String validTypeResult(double doubleResult) {
        int intResult = (int) doubleResult;
        if (intResult == doubleResult) return String.valueOf(intResult);
        else return String.valueOf(doubleResult);
    }
}