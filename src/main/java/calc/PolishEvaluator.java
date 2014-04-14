package calc;

import java.util.Stack;

public class PolishEvaluator extends Calculation {
    public Double evaluator(String expression) {
        Stack<Double> expStack = new Stack<Double>();
        StringBuilder currentNumber = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            if (isPlus(token)) {
                expStack.push(calculation(token, expStack.pop(), expStack.pop()));
            } else if (isMinus(token)) {
                expStack.push(calculation(token, expStack.pop(), expStack.pop()));
            } else if (isDivision(token)) {
                expStack.push(calculation(token, expStack.pop(), expStack.pop()));
            } else if (isMultiply(token)) {
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


