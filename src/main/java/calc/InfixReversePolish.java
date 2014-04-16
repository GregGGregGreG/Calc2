package calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import static calc.CalculationUtil.*;

public class InfixReversePolish {
    private StringBuilder str = new StringBuilder();
    private Stack<Character> operator = new Stack<Character>();
    private Deque<Character> expDeque = new ArrayDeque<Character>();

    public String parser(String expression) throws Exception {
        String safeExpression = expression.trim().replaceAll(" ", "").replaceAll(",", ".")
                .replaceAll("--", "+").replace("+-", "-").replace("(-", "(0-").replace("/0", "&");
        if (expression.charAt(0) == '-') {
            safeExpression = "0" + safeExpression;
        }
        char[] chars = safeExpression.toCharArray();
        for (char token : chars) {
            expDeque.addLast(token);
        }
        while (!expDeque.isEmpty()) {
            char token = expDeque.pollFirst();
            if (Character.isDigit(token) || token == '.') {
                str.append(token);
            } else if (isNotPriority(token)) {
                cleanStackOperator();
                operator.push(token);
            } else if (isPriority(token)) {
                str.append(' ');
                operator.push(token);
            } else if (isCloseBracket(token)) {
                cleanStackBracket();
            } else if (isOpenBracket(token)) {
                operator.push(token);
            } else if (token == '&') {
                throw new ArithmeticException("Деление на ноль невозможно ");
            } else {
                throw new Exception("Token is not supported = " + token);
            }
        }
        cleanStackBracket();
        System.out.println(str);
        operator.clear();
        String result = str.toString();
        str = new StringBuilder();
        expDeque.clear();
        return result;
    }

    private void cleanStackBracket() {
        str.append(' ');
        char lastOperator = ' ';
        while (!(isOpenBracket(lastOperator)) && !(operator.isEmpty())) {
            str.append(lastOperator = operator.pop());
        }
    }

    private void cleanStackOperator() {
        str.append(' ');
        while (!operator.isEmpty() && !isOpenBracket(operator.peek())) {
            str.append(operator.pop());
        }
    }
}

