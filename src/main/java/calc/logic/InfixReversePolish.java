package calc.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static calc.logic.CalculationUtil.*;
import static calc.logic.PolishEvaluator.evaluator;

public class InfixReversePolish {
    private StringBuilder evaluation = new StringBuilder();
    private Deque<Character> operators = new LinkedList<Character>();
    private Deque<Character> expressions = new ArrayDeque<Character>();

    public Double parser(String expression) throws Exception {
        String safeExpression = expression.trim().replaceAll(" ", "").replaceAll(",", ".")
                .replaceAll("--", "+").replaceAll("\\+\\-", "-").replaceAll("\\(\\-", "(0-")
                .replaceAll("/0 ", "&").replaceAll("^-", "0-")
                .replaceAll("/-", "/(0-").replaceAll("\\*-", "*(0-").replaceAll("×", "\\*")
                .replaceAll("÷", "\\/");
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
                clearAllValues();
                throw new Exception("Token is not supported = " + token);

            }
        }
        cleanStackBracket();
        cleanStackOperator();
        String result = evaluation.toString();
        System.out.println(evaluation);
        clearAllValues();
        return evaluator(result);
    }

    private void cleanStackBracket() {
        evaluation.append(' ');
        char lastOperator = ' ';
        while (!(isOpenBracket(lastOperator)) && !(operators.isEmpty())) {
            evaluation.append(lastOperator = operators.pollLast());
        }
    }

    private void cleanStackOperator() {
        evaluation.append(' ');
        while (!operators.isEmpty() && !isOpenBracket(operators.peekLast())) {
            evaluation.append(operators.pollLast());
        }
    }

    private void clearAllValues() {
        operators.clear();
        evaluation = new StringBuilder();
        expressions.clear();
    }
}

