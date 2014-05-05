package calc.logic;

import calc.exceptions.ExceptionPolishEvaluator;

import java.util.ArrayDeque;
import java.util.Deque;

import static calc.logic.CalculationUtil.calculation;
import static calc.logic.CalculationUtil.isOperators;

public class PolishEvaluator {
    public static Double evaluator(String expression) throws ExceptionPolishEvaluator {
        Deque<Double> expStack = new ArrayDeque<Double>();
        StringBuilder currentNumber = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            if (isOperators(token)) {
                if (expStack.size() > 1)
                    expStack.addLast(calculation(token, expStack.pollLast(), expStack.pollLast()));
                else {
                    throw new ExceptionPolishEvaluator("Invalid expression");
                }
            } else if (Character.isDigit(token) || token == '.') {
                currentNumber.append(token);
            } else if (token == ' ' && !(currentNumber.length() == 0)) {
                expStack.addLast(Double.parseDouble(String.valueOf(currentNumber)));
                currentNumber = new StringBuilder();
            }
        }
        if (expStack.size() == 1) return expStack.pollLast();
        else throw new ExceptionPolishEvaluator("Invalid expression");
    }
}
