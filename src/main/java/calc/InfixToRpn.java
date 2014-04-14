package calc;

import java.util.ArrayDeque;
import java.util.Deque;

public class InfixToRpn extends TypesOfOperators {

    public String parser(String expression) {
        StringBuilder str = new StringBuilder();
        Deque<Character> operator = new ArrayDeque<Character>();
        Deque<Character> expDeque = new ArrayDeque<Character>();

        char[] chars = expression.toCharArray();
        for (char token : chars) {
            expDeque.addLast(token);
        }

        while (!expDeque.isEmpty()) {
            Character token = expDeque.pollFirst();
            if (isPlus(token) || isMinus(token)) {
                str.append(' ');
                operator.push(token);
                operator.push(' ');
            } else if (isMultiply(token) || isDivision(token)) {
                str.append(' ');
                operator.addFirst(token);
                operator.addFirst(' ');
                str.append(expDeque.pollFirst());
                while (!(operator.isEmpty())) {
                    str.append(operator.pollFirst());
                }
            } else if (Character.isDigit(token) || token == '.') {
                str.append(token);
            }
        }
        while (!(operator.isEmpty())) {
            str.append(operator.pollFirst());
        }
        System.out.println(str);
        return String.valueOf(str);
    }
}

