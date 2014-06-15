package calculator.logic;

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

    public static boolean is(Operator isOperator) {
        for (Operator operator : Operator.values()) {
            if (operator == isOperator) return true;
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

    public static boolean isOpenBracket(Operator operator) {
        return operator == OPENBRACKET;
    }

    public static boolean isCloseBracket(Operator operator) {
        return operator == CLOSEBRACKET;
    }


    public static boolean isPriority(Operator operator) {
        return operator == DIVISION || operator == MULTIPLY || isBinary(operator) || operator == POW;
    }

    public static boolean isBinary(Operator operator) {
        return operator == SQRT || operator == COS;
    }

    public static boolean isNotPriority(Operator operator) {
        return operator == PLUS || operator == MINUS;
    }


    public static Double calculation(Operator operator, Double right, Double left) {
        Double result = null;
        switch (operator) {
            case PLUS:
                result = left + right;
                break;
            case MINUS:
                result = left - right;
                break;
            case MULTIPLY:
                result = left * right;
                break;
            case DIVISION:
                result = left / right;
                break;
            case POW:
                result = Math.pow(left, right);
                break;
        }
        return result;
    }

    public static Double calculation(Operator operator, Double digit) {
        Double result = null;
        switch (operator) {
            case SQRT:
                result = Math.sqrt(digit);
                break;
            case COS:
                result =Math.cos(digit);
                break;
        }
        return result;
    }
}