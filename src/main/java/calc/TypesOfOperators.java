package calc;

public class TypesOfOperators {
    static boolean isOperators(char token) {
        return isPlus(token) || isMinus(token) || isDivision(token) || isMultiply(token);

    }

    static boolean isMultiply(char token) {
        return token == '*';
    }

    static boolean isDivision(char token) {
        return token == '/';
    }

    static boolean isMinus(char token) {
        return token == '-';
    }

    static boolean isPlus(char token) {
        return token == '+';
    }

    static boolean isOpenBracket(char token) {
        return token == '(';
    }

    static boolean isCloseBracket(char token) {
        return token == ')';
    }
}
