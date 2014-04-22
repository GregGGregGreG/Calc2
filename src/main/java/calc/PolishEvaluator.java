package calc;

import java.util.*;

import static calc.CalculationUtil.calculation;
import static calc.CalculationUtil.isOperators;

public class PolishEvaluator {
    public Double evaluator(String expression) {
        Deque<Double> expStack = new ArrayDeque<Double>();
        StringBuilder currentNumber = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            if (isOperators(token)) {
                expStack.addLast(calculation(token, expStack.pollLast(), expStack.pollLast()));
            } else if (Character.isDigit(token) || token == '.') {
                currentNumber.append(token);
            } else if (token == ' ' && !(currentNumber.length() == 0)) {
                expStack.addLast(Double.parseDouble(String.valueOf(currentNumber)));
                currentNumber = new StringBuilder();
            }
        }
        return expStack.pollLast();
    }
}


