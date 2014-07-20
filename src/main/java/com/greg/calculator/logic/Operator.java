package com.greg.calculator.logic;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operator {
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('*'),
    DIVISION('/'),
    OPENBRACKET('('),
    CLOSEBRACKET(')'),
    POW('^'),
    COS('c'),
    SQRT('p');

    private final Character token;

    Operator(Character token) {
        this.token = token;
    }

    public Character getToken() {
        return token;
    }

    public static final MathContext mc = new MathContext(11);

    public static Operator of(Character token) {
        for (Operator operator : Operator.values()) {
            if (operator.token == token) return operator;
        }
        throw new RuntimeException("Token " + token + " is not supported!");
    }

    public static boolean is(Character token) {
        for (Operator operator : Operator.values()) {
            if (operator.token == token) return true;
        }
        return false;
    }

    public static boolean isBasic(char token) {
        return token == '+' || token == '-' || token == '/' || token == '*' || token == '÷' || token == '×' ||
                token == '^' || token == '√' || token == 'c';
    }

    public static boolean isOpenBracket(char token) {
        return token == '(';
    }

    public static boolean isCloseBracket(char token) {
        return token == ')';
    }

    public static boolean isPriority(Operator operator) {
        return operator == DIVISION || operator == MULTIPLY || isBinary(operator) || operator == POW;
    }

    public static boolean isNotPriority(Operator operator) {
        return operator == PLUS || operator == MINUS;
    }

    public static boolean isBinary(Operator operator) {
        return operator == SQRT || operator == COS;
    }

    public static BigDecimal calculation(Operator operator, BigDecimal second, BigDecimal first) {
        BigDecimal result = null;
        switch (operator) {
            case PLUS:
                result = first.add(second, mc);
                break;
            case MINUS:
                result = first.subtract(second, mc);
                break;
            case MULTIPLY:
                result = first.multiply(second, mc);
                break;
            case DIVISION:
                result = first.divide(second, mc);
                break;
            case POW:
                result = new BigDecimal(String.valueOf((Math.pow(first.doubleValue(), second.doubleValue()))), mc);
                break;
        }
        return result;
    }

    public static BigDecimal calculation(Operator operator, BigDecimal digit) {
        Double dDigit = digit.doubleValue();
        BigDecimal result = null;
        switch (operator) {
            case SQRT:
                result = new BigDecimal(String.valueOf(Math.sqrt(dDigit)), mc);
                break;
            case COS:
                result = new BigDecimal(String.valueOf(Math.cos(dDigit)), mc);
                break;
        }
        return result;
    }

    public static String typeDigit(BigDecimal bdNumber) {
        return bdNumber.toString().replaceAll("\\.0$", "");
    }
}