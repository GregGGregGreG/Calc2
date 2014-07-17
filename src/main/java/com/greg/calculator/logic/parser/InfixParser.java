package com.greg.calculator.logic.parser;

import com.greg.calculator.logic.ExceptionParserPolishNotation;
import com.greg.calculator.logic.Operator;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static com.greg.calculator.logic.CalculationUtil.validTypeResult;

@Component
public class InfixParser implements ParserExpression {

    private static StringBuilder number = new StringBuilder();
    private static Deque<Double> operands = new LinkedList<>();
    private static Deque<Operator> operators = new LinkedList<>();
    private static Deque<Character> expression = new ArrayDeque<>();


    public String parser(String str) throws ExceptionParserPolishNotation {
        for (char token : safeExpression(str)) {
            expression.addLast(token);
        }

        while (expression.iterator().hasNext()) {
            char token = expression.pollFirst();
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
                } else if (Operator.CLOSEBRACKET.equals(operator)) {
                    pushBracket();
                } else if (Operator.OPENBRACKET.equals(operator)) {
                    operators.add(operator);
                }
            } else {
                cleanParser();
                throw new ExceptionParserPolishNotation("Token number is not supported = " + token);
            }
        }
        pushBracket();
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
                .replaceAll("√", "p")                   // Valid sqrt sign
                .replaceAll("cos", "c")                  //Valid cos
                .toCharArray();
    }

    private static void pushBracket() {
        if (number.length() > 0) operands.add(Double.valueOf(String.valueOf(number)));
        while (!operators.isEmpty()) {
            if (Operator.OPENBRACKET.equals(operators.peekLast())) {
                operators.removeLast();
                return;
            }
            calculation();
        }
    }

    private static void pushOperators() {
        while ((operands.size() >= 2 && !Operator.OPENBRACKET.equals(operators.peekLast()))
                || Operator.isBinary(operators.peekLast())) {
            calculation();
        }
    }

    private static void calculation()  {
        try {
            Operator operator = (operators.pollLast());
            if (Operator.isBinary(operator)) {
                operands.addLast(Operator.calculation(operator, operands.pollLast()));
            } else {
                operands.addLast(Operator.calculation(operator, operands.pollLast(), operands.pollLast()));
            }
        } catch (NullPointerException e) {
            System.out.println("Invalid expression");
        }
    }

    private static void cleanParser() {
        operators.clear();
        number = new StringBuilder();
        expression.clear();
    }

}