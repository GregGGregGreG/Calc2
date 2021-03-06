package com.greg.calculator.logic;

import java.math.BigDecimal;

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


}