package calc.logic;

import calc.exceptions.PolishEvaluatorException;

import java.util.ArrayDeque;
import java.util.Deque;

import static calc.logic.CalculationUtil.*;

public class PolishEvaluator {
    public static Double evaluator(String expression) throws PolishEvaluatorException {
        Deque<Double> expStack = new ArrayDeque<Double>();
        StringBuilder currentNumber = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            if (EOperator.IS_OPERATOR.isOpr(token)) {
                if (expStack.size() > 1 || (expStack.size() == 1 && EOperator.BINARY_OPERATION.isOpr(token)))
                    if (EOperator.BINARY_OPERATION.isOpr(token)) {
                        expStack.addLast(calculationBinaryOperation(token, expStack.pollLast()));
                    } else {
                        expStack.addLast(calculation(token, expStack.pollLast(), expStack.pollLast()));
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