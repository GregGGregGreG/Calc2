package calc;

public class Calculation extends TypesOfOperators {
    static Double calculation(Character token, Double right, Double left) {
        Double result = null;
        if (isPlus(token)) {
            result = left + right;
        }
        if (isMinus(token)) {
            result = left - right;
        }
        if (isMultiply(token)) {
            result = left * right;
        }
        if (isDivision(token)) {
            result = left / right;
        }
        return result;
    }
}
