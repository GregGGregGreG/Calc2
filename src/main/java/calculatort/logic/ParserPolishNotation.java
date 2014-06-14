package calculatort.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static calculatort.logic.CalculationUtil.validTypeResult;

public class ParserPolishNotation {

    private static StringBuilder number = new StringBuilder();
    private static Deque<Double> operands = new LinkedList<>();
    private static Deque<Operator> operators = new LinkedList<>();
    private static Deque<Character> expressions = new ArrayDeque<>();

    public static String parser(String expression) throws ExceptionParserPolishNotatio {
        for (char token : safeExpression(expression)) {
            expressions.addLast(token);
        }
        while (!expressions.isEmpty()) {
            char token = expressions.pollFirst();
            if (Character.isDigit(token) || token == '.' || token == 'E') {
                number.append(token);
            } else if (Operator.is(token)) {
                if (number.length() > 0) operands.add(Double.valueOf(String.valueOf(number)));
                number = new StringBuilder();
                Operator operator = Operator.of(token);
                if (Operator.isNotPriority(operator)) {
                    pushOperators();
                    operators.add(operator);
                } else if (Operator.isPriority(operator)) {
                    operators.add(operator);
                } else if (operator == Operator.CLOSEBRACKET) {
                    pushBracket();
                } else if (operator == Operator.OPENBRACKET) {
                    operators.add(operator);
                }
            } else {
                cleanParser();
                throw new ExceptionParserPolishNotatio("Token is not supported = " + token);
            }
        }
        pushBracket();
//        pushOperators();
        cleanParser();

        return validTypeResult(operands.pollLast());
    }

    private static char[] safeExpression(String expression) {
        return expression.trim()
                .replaceAll(" ", "")                    // Deleting space
                .replaceAll(",", ".")                   // Replacement point for comma
                .replaceAll("×", "\\*")                 // Valid multiplication sign
                .replaceAll("÷", "\\/")                 // Valid division sign
                .replaceAll("--", "+")                  // Processing of two minuses
                .replaceAll("\\+\\-", "-")              // Plus to minus gives a minus.
                .replaceAll("\\(\\-", "(0-")            // Processing of negative numbers
                .replaceAll("/0 ", "&")                 // ------------------------------
                .replaceAll("^-", "0-")                 // If a negative number -  first symbol
                .replaceAll("/-", "/(0-")               // Division by a negative number
                .replaceAll("\\*\\-", "\\*(\\0-")       // Multiplication by a negative number
                .toCharArray();
    }

    private static void pushBracket() {
        if (number.length() > 0) operands.add(Double.valueOf(String.valueOf(number)));
        while (!operators.isEmpty()) {
            if (Operator.isOpenBracket(operators.peekLast())) {
                operators.removeLast();
                return;
            }
            calculation();
        }
    }

    /*
    Defecating stack operators to open bracket operator inclusive.
     */
    private static void pushOperators() {
        while (operands.size() >= 2 && !Operator.isOpenBracket(operators.peekLast())) {
            calculation();
        }
    }

    private static void calculation() {
        Operator operator = (operators.pollLast());
        if (Operator.isBinary(operator)) {
            operands.addLast(Operator.calculation(operator, operands.pollLast()));
        } else {
            operands.addLast(Operator.calculation(operator, operands.pollLast(), operands.pollLast()));
        }
    }

    private static void cleanParser() {
        operators.clear();
        number = new StringBuilder();
        expressions.clear();
    }
}