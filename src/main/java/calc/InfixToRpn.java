package calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class InfixToRpn extends TypesOfOperators {
    static StringBuilder str = new StringBuilder();
    static Stack<Character> operator = new Stack<Character>();
    static Deque<Character> expDeque = new ArrayDeque<Character>();


    public String parser(String expression) {
        char[] chars = expression.toCharArray();
        for (char token : chars) {
            expDeque.addLast(token);
        }
        while (!expDeque.isEmpty()) {
            Character token = expDeque.pollFirst();
            if (Character.isDigit(token) || token == '.') {
                str.append(token);
            }
            if (isPlus(token) || isMinus(token)) {
                str.append(' ');
                cleanStack();
                str.append(' ');
                operator.push(token);
            }
            if (isMultiply(token) || isDivision(token)) {
                str.append(' ');
                operator.push(token);
            }
            if (isCloseBracket(token)) {
                str.append(' ');

                cleanStack();
            }
            if (isOpenBracket(token)) {
                str.append(' ');
                operator.push(token);
            }
        }
        str.append(' ');
        cleanStack();
        System.out.println(str);
        return String.valueOf(str);
    }

    public static void cleanStack() {
        Character lastOperator = null;
        if (!operator.isEmpty()) {
            lastOperator = operator.pop();
        } else {
            return;
        }
        while (!(lastOperator == '(') && !operator.isEmpty()) {
            str.append(lastOperator);
            if (!operator.isEmpty()) lastOperator = operator.pop();
        }
        if (!isOpenBracket(lastOperator)) str.append(lastOperator);
        else lastOperator = null;
    }
}

