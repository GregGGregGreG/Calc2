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
        if (EOperator.PLUS.isOpr(token)) return left + right;
        if (EOperator.MINUS.isOpr(token)) return left - right;
        if (EOperator.MULTIPLY.isOpr(token)) return left * right;
        if (EOperator.DIVISION.isOpr(token)) return left / right;
        if (EOperator.POW.isOpr(token)) return Math.pow(left, right);
        return null;
    }

    public static Double calculationBinaryOperation(Character token, Double digit) {
        return Math.sqrt(digit);
    }

}