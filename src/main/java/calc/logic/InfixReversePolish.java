package calc.logic;

import calc.exceptions.ExceptionInfixReversPolish;
import calc.exceptions.ExceptionPolishEvaluator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static calc.logic.CalculationUtil.*;
import static calc.logic.PolishEvaluator.evaluator;

public class InfixReversePolish {
    private static StringBuilder evaluation = new StringBuilder();
    private static Deque<Character> operators = new LinkedList<Character>();
    private static Deque<Character> expressions = new ArrayDeque<Character>();

    public static Double parser(String expression) throws ExceptionPolishEvaluator,ExceptionInfixReversPolish {
        String safeExpression = expression.trim().replaceAll(" ", "").replaceAll(",", ".").replaceAll("×", "\\*").replaceAll("÷", "\\/")
                .replaceAll("--", "+").replaceAll("\\+\\-", "-").replaceAll("\\(\\-", "(0-")
                .replaceAll("/0 ", "&").replaceAll("^-", "0-")
                .replaceAll("/-", "/(0-").replaceAll("\\*\\-", "\\*(\\0-");
        char[] chars = safeExpression.toCharArray();
        for (char token : chars) {
            expressions.addLast(token);
        }
        while (!expressions.isEmpty()) {
            char token = expressions.pollFirst();
            if (Character.isDigit(token) || token == '.') {
                evaluation.append(token);
            } else if (isNotPriority(token)) {
                cleanStackOperator();
                operators.add(token);
            } else if (isPriority(token)) {
                evaluation.append(' ');
                operators.add(token);
            } else if (isCloseBracket(token)) {
                cleanStackBracket();
            } else if (isOpenBracket(token)) {
                operators.add(token);
            } else {
                clearAllValuesInStack();
                throw new ExceptionInfixReversPolish("Token is not supported = " + token);
            }
        }
        cleanStackBracket();
        cleanStackOperator();
        String result = evaluation.toString();
        System.out.println(evaluation);
        clearAllValuesInStack();
        return evaluator(result);
    }

    private static void cleanStackBracket() {
        evaluation.append(' ');
        char lastOperator = ' ';
        while (!(isOpenBracket(lastOperator)) && !(operators.isEmpty())) {
            evaluation.append(lastOperator = operators.pollLast());
        }
    }

    /*
    Defecating stack operators to open bracket opereta inclusive.
     */
    private static void cleanStackOperator() {
        evaluation.append(' ');
        while (!operators.isEmpty() && !isOpenBracket(operators.peekLast())) {
            evaluation.append(operators.pollLast());
        }
    }

    private static void clearAllValuesInStack() {
        operators.clear();
        evaluation = new StringBuilder();
        expressions.clear();
    }
}