package calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static calc.CalculationUtil.*;

 public class InfixReversePolish {
    private StringBuilder evaluation = new StringBuilder();
    private Deque<Character> operators = new LinkedList<Character>();
    private Deque<Character> expressions = new ArrayDeque<Character>();

    public String parser(String expression) //throws Exception
     {
        String safeExpression = expression.trim().replaceAll(" ", "").replaceAll(",", ".")
                .replaceAll("--", "+").replaceAll("\\+\\-", "-").replaceAll("\\(\\-", "(0-")
                .replaceAll("/0 ", "&").replaceAll("^-", "0-")
                .replaceAll("/-", "/(0-").replaceAll("\\*-", "*(0-").replaceAll("×", "\\*")
                .replaceAll("÷","\\/");
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
                operators.add(token);}
//            } else if (token == '&') {
//                throw new ArithmeticException("Деление на ноль невозможно ");
//            } else {
//                throw new Exception("Token is not supported = " + token);
//            }
        }
        cleanStackBracket();
        cleanStackOperator();

        System.out.println(evaluation);
        operators.clear();
        String result = evaluation.toString();
        evaluation = new StringBuilder();
        expressions.clear();

        PolishEvaluator evaluator = new PolishEvaluator();
        return String.valueOf(evaluator.evaluator(result));
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
}

