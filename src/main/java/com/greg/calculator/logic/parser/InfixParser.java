package com.greg.calculator.logic.parser;

import com.greg.calculator.logic.ExceptionParserPolishNotation;
import com.greg.calculator.logic.Operator;
import com.greg.calculator.logic.calculation.Calculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

@Component
public class InfixParser implements ParserExpression {

    public static final Character FLOATING_POINT = 'E';
    public static final Character POINT = '.';

    public static final int MIN_SIZE_NUMBER = 0;
    public static final int MIN_QUANTITY_OPERANDS = 2;

    private static StringBuilder number = new StringBuilder();
    private static Deque<BigDecimal> operands = new LinkedList<>();
    private static Deque<Operator> operators = new LinkedList<>();
    private static Deque<Character> expression = new ArrayDeque<>();


    @Qualifier(value = "calculations")
    @Autowired
    private Calculations calculation;

    public String parser(String str) throws ExceptionParserPolishNotation {
        for (char token : safeExpression(str)) {
            expression.addLast(token);
        }

        while (expression.iterator().hasNext()) {
            char token = expression.pollFirst();
            if (Character.isDigit(token) || token == POINT || token == FLOATING_POINT) {
                number.append(token);
            } else if (Operator.is(token)) {
                if (number.length() > MIN_SIZE_NUMBER) operands.add(new BigDecimal(String.valueOf(number)));
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
        return Operator.typeDigit(operands.pollLast());
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
                .replaceAll("E\\+", "E")                   //Valid point floating
                .toCharArray();
    }

    private void pushBracket() {
        if (number.length() > MIN_SIZE_NUMBER) operands.add(new BigDecimal(String.valueOf(number)));
        while (!operators.isEmpty()) {
            if (Operator.OPENBRACKET.equals(operators.peekLast())) {
                operators.removeLast();
                return;
            }
            calculation();
        }
    }

    private void pushOperators() {
        while ((operands.size() >= MIN_QUANTITY_OPERANDS && !Operator.OPENBRACKET.equals(operators.peekLast()))
                || Operator.isBinary(operators.peekLast())) {
            calculation();
        }
    }

    private void calculation() {
        try {
            Operator operator = (operators.pollLast());
            if (Operator.isBinary(operator)) {
                operands.addLast(calculation.calculation(operator, operands.pollLast()));
            } else {
                operands.addLast(calculation.calculation(operator, operands.pollLast(), operands.pollLast()));
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