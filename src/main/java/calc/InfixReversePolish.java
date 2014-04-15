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
        final String safeExpression = expression.trim().replaceAll(" ", "").replaceAll(",", ".");
        char[] chars = safeExpression.toCharArray();
        for (char token : chars) {
            expDeque.addLast(token);
        }
        while (!expDeque.isEmpty()) {
            char token = expDeque.pollFirst();
            if (Character.isDigit(token) || token == '.') {
                str.append(token);
            } else if (isNotPriority(token)) {
                cleanStack();
                str.append(' ');
                operator.push(token);
            } else if (isPriority(token)) {
                str.append(' ');
                operator.push(token);
            } else if (isCloseBracket(token)) {
                cleanStack();
            } else if (isOpenBracket(token)) {
                str.append(' ');
                operator.push(token);
            } else {
                throw new Exception("Token is not supported = " + token);
            }
        }
        cleanStack();
        System.out.println(str);
operator.clear();
        String result = str.toString();
        str = new StringBuilder();
        expDeque.clear();
        return result;
    }

    private void cleanStack() {
        str.append(' ');
        char lastOperator = ' ';
        while (!isOpenBracket(lastOperator) && !operator.isEmpty()) {
            str.append(lastOperator = operator.pop());
        }
    }
}

