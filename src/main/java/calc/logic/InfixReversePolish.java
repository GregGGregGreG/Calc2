package calc.logic;

import calc.exceptions.InfixReversPolishException;
import calc.exceptions.PolishEvaluatorException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static calc.logic.CalculationUtil.validTypeResult;
import static calc.logic.PolishEvaluator.evaluator;

public class InfixReversePolish {
    private static StringBuilder evaluation = new StringBuilder();
    private static Deque<Number> numbers = new LinkedList<>();
    private static Deque<Character> operators = new LinkedList<>();
    private static Deque<Character> expressions = new ArrayDeque<>();

    public static String parser(String expression) throws PolishEvaluatorException, InfixReversPolishException {
        for (char token : safeExpression(expression)) {
            expressions.addLast(token);
        }
        while (!expressions.isEmpty()) {
            char token = expressions.pollFirst();
            if (Character.isDigit(token) || token == '.' || token == 'E') {
                evaluation.append(token);
            } else if (Operator.is(token)) {
                Operator operator = Operator.of(token);
                if (Operator.isNotPriority(operator)) {
                    cleanStackOperator();
                    operators.add(operator.getToken());
                } else if (Operator.isPriority(operator)) {
                    evaluation.append(' ');
                    operators.add(operator.getToken());
                } else if (operator == Operator.CLOSEBRACKET) {
                    cleanStackBracket();
                } else if (operator == Operator.OPENBRACKET) {
                    operators.add(operator.getToken());
                }
            } else {
                clearAllValuesInStack();
                throw new InfixReversPolishException("Token is not supported = " + token);
            }
        }
        cleanStackBracket();
        cleanStackOperator();
        String result = evaluation.toString();
        clearAllValuesInStack();

        Double calculationExpression = evaluator(result);
        return validTypeResult(calculationExpression);

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
        evaluation.append(' ');
        while (!operators.isEmpty()) {
            if (Operator.isOpenBracket(operators.peekLast())) {
                operators.removeLast();
                return;
            }
            evaluation.append(operators.pollLast());
        }
    }

    /*
    Defecating stack operators to open bracket opereta inclusive.
     */
    private static void cleanStackOperator() {
        evaluation.append(' ');
        while (!operators.isEmpty() && !Operator.isOpenBracket(operators.peekLast())) {
            evaluation.append(operators.pollLast());
        }
    }

    private static void clearAllValuesInStack() {
        operators.clear();
        evaluation = new StringBuilder();
        expressions.clear();
    }
}