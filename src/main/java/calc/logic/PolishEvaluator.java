package calc.logic;

import calc.exceptions.PolishEvaluatorException;

import java.util.ArrayDeque;
import java.util.Deque;

public class PolishEvaluator {
    public static Double evaluator(String expression) throws PolishEvaluatorException {
        Deque<Double> expStack = new ArrayDeque<Double>();
        StringBuilder currentNumber = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            if (Operator.is(token)) {
                Operator operator = Operator.of(token);
                if (expStack.size() > 1 || (expStack.size() == 1 && Operator.isBinary(operator)))
                    if (Operator.isBinary(operator)) {
                        expStack.addLast(Operator.calculation(operator, expStack.pollLast()));
                    } else {
                        expStack.addLast(Operator.calculation(operator, expStack.pollLast(), expStack.pollLast()));
                    }
                else {
                    throw new PolishEvaluatorException("Invalid expression");
                }
            } else if (Character.isDigit(token) || token == '.' || token == 'E') {
                currentNumber.append(token);
            } else if (token == ' ' && !(currentNumber.length() == 0)) {
                expStack.addLast(Double.parseDouble(String.valueOf(currentNumber)));
                currentNumber = new StringBuilder();
            }
        }
        if (expStack.size() == 1) return expStack.pollLast();
        else throw new PolishEvaluatorException("Invalid expression");
    }
}