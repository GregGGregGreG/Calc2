package calc;

import java.util.Stack;

import static calc.CalculationUtil.calculation;
import static calc.CalculationUtil.isOperators;

public class PolishEvaluator {
    public Double evaluator(String expression) throws Exception {
        Stack<Double> expStack = new Stack<Double>();
        StringBuilder currentNumber = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            if (isOperators(token)) {
                expStack.push(calculation(token, expStack.pop(), expStack.pop()));
            } else if (Character.isDigit(token) || token == '.') {
                currentNumber.append(token);
            } else if (token == ' ' && !(currentNumber.length() == 0)) {
                expStack.push(Double.parseDouble(String.valueOf(currentNumber)));
                currentNumber = new StringBuilder();
            }
        }
        return expStack.pop();
    }
}


