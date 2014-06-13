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
    private static Deque<Character> operators = new LinkedList<Character>();
    private static Deque<Character> expressions = new ArrayDeque<Character>();

    public static String parser(String expression) throws PolishEvaluatorException, InfixReversPolishException {
        String safeExpression = expression.trim().replaceAll(" ", "").replaceAll(",", ".").replaceAll("×", "\\*")
                .replaceAll("÷", "\\/").replaceAll("--", "+").replaceAll("\\+\\-", "-").replaceAll("\\(\\-", "(0-")
                .replaceAll("/0 ", "&").replaceAll("^-", "0-").replaceAll("/-", "/(0-").replaceAll("\\*\\-", "\\*(\\0-");
        char[] chars = safeExpression.toCharArray();
        for (char token : chars) {
            expressions.addLast(token);
        }
        while (!expressions.isEmpty()) {
            char token = expressions.pollFirst();
            if (Character.isDigit(token) || token == '.' || token == 'E') {
                evaluation.append(token);
            } else if (EOperator.IS_NOT_PRIORITY_OPERATOR.isOpr(token)) {
                cleanStackOperator();
                operators.add(token);
            } else if (EOperator.IS_PRIORITY_OPERATOR.isOpr(token)) {
                evaluation.append(' ');
                operators.add(token);
            } else if (EOperator.CLOSEBRACKET.isOpr(token)) {
                cleanStackBracket();
            } else if (EOperator.OPENBRACKET.isOpr(token)) {
                operators.add(token);
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

    private static void cleanStackBracket() {
        evaluation.append(' ');
        char lastOperator = ' ';
        while (!(EOperator.OPENBRACKET.isOpr(lastOperator)) && !(operators.isEmpty())) {
            evaluation.append(lastOperator = operators.pollLast());
        }
    }

    /*
    Defecating stack operators to open bracket opereta inclusive.
     */
    private static void cleanStackOperator() {
        evaluation.append(' ');
        while (!operators.isEmpty() && !EOperator.OPENBRACKET.isOpr(operators.peekLast())) {
            evaluation.append(operators.pollLast());
        }
    }

    private static void clearAllValuesInStack() {
        operators.clear();
        evaluation = new StringBuilder();
        expressions.clear();
    }
}