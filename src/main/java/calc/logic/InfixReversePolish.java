package calc.logic;

import calc.exceptions.InfixReversPolishException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static calc.logic.CalculationUtil.validTypeResult;

public class InfixReversePolish {
    private static StringBuilder evaluation = new StringBuilder();
    private static Deque<Double> numbers = new LinkedList<>();
    private static Deque<Operator> operators = new LinkedList<>();
    private static Deque<Character> expressions = new ArrayDeque<>();

    public static String parser(String expression) throws  InfixReversPolishException {
        for (char token : safeExpression(expression)) {
            expressions.addLast(token);
        }
        while (!expressions.isEmpty()) {
            char token = expressions.pollFirst();
            if (Character.isDigit(token) || token == '.' || token == 'E') {
                evaluation.append(token);
            } else if (Operator.is(token)) {
                if (evaluation.length() > 0) numbers.add(Double.valueOf(String.valueOf(evaluation)));
                evaluation = new StringBuilder();
                Operator operator = Operator.of(token);
                if (Operator.isNotPriority(operator)) {
                    cleanStackOperator();
                    operators.add(operator);
                } else if (Operator.isPriority(operator)) {
                    operators.add(operator);
                } else if (operator == Operator.CLOSEBRACKET) {
                    cleanStackBracket();
                } else if (operator == Operator.OPENBRACKET) {
                    operators.add(operator);
                }
            } else {
                clearAllValuesInStack();
                throw new InfixReversPolishException("Token is not supported = " + token);
            }
        }
        if (evaluation.length() > 0) numbers.add(Double.valueOf(String.valueOf(evaluation)));
        evaluation = new StringBuilder();
        cleanStackBracket();
        cleanStackOperator();
        clearAllValuesInStack();

        return validTypeResult(numbers.pollLast());

    }

    private static char[] safeExpression(String expression) {
        return expression.trim()
                .replaceAll(" ", "")
                .replaceAll(",", ".")
                .replaceAll("×", "\\*")
                .replaceAll("÷", "\\/")
                .replaceAll("--", "+")
                .replaceAll("\\+\\-", "-")
                .replaceAll("\\(\\-", "(0-")
                .replaceAll("/0 ", "&")
                .replaceAll("^-", "0-")
                .replaceAll("/-", "/(0-")
                .replaceAll("\\*\\-", "\\*(\\0-")
                .toCharArray();
    }

    private static void cleanStackBracket() {
        while (!operators.isEmpty() && !numbers.isEmpty()) {
            if (Operator.isOpenBracket(operators.peekLast())) {
                operators.removeLast();
                return;
            }
            Operator operator = (operators.pollLast());
            if (Operator.isBinary(operator)) {
                numbers.addLast(Operator.calculation(operator, numbers.pollLast()));
            } else {
                numbers.addLast(Operator.calculation(operator, numbers.pollLast(), numbers.pollLast()));
            }
        }
    }

    /*
    Defecating stack operators to open bracket opereta inclusive.
     */
    private static void cleanStackOperator() {

        while (numbers.size() >= 2 && !Operator.isOpenBracket(operators.peekLast())) {
            Operator operator = (operators.pollLast());
            if (Operator.isBinary(operator)) {
                numbers.addLast(Operator.calculation(operator, numbers.pollLast()));
            } else {
                numbers.addLast(Operator.calculation(operator, numbers.pollLast(), numbers.pollLast()));
            }
        }
    }

    private static void clearAllValuesInStack() {
        operators.clear();
        evaluation = new StringBuilder();
        expressions.clear();
    }
}