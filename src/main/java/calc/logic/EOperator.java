package calc.logic;

public enum EOperator {

    PLUS {
        @Override
        public boolean isOpr(Character token) {
            return token == '+';
        }
    },
    MINUS {
        @Override
        public boolean isOpr(Character token) {
            return token == '-';
        }
    },
    MULTIPLY {
        @Override
        public boolean isOpr(Character token) {
            return token == '*';
        }
    },
    DIVISION {
        @Override
        public boolean isOpr(Character token) {
            return token == '/';
        }
    },
    OPENBRACKET {
        @Override
        public boolean isOpr(Character token) {
            return token == '(';
        }
    },
    CLOSEBRACKET {
        @Override
        public boolean isOpr(Character token) {
            return token == ')';
        }
    },
    POW {
        @Override
        public boolean isOpr(Character token) {
            return token == '^';
        }
    },
    SQRT {
        @Override
        public boolean isOpr(Character token) {
            return token == '√';
        }
    },
    IS_OPERATOR {
        @Override
        public boolean isOpr(Character token) {
            return IS_PRIORITY_OPERATOR.isOpr(token) || IS_NOT_PRIORITY_OPERATOR.isOpr(token);
        }
    },
    BINARY_OPERATION {
        @Override
        public boolean isOpr(Character token) {
            return SQRT.isOpr(token);
        }
    },
    IS_PRIORITY_OPERATOR {
        @Override
        public boolean isOpr(Character token) {
            return DIVISION.isOpr(token) || MULTIPLY.isOpr(token) || BINARY_OPERATION.isOpr(token) || POW.isOpr(token);
        }
    },
    IS_NOT_PRIORITY_OPERATOR {
        @Override
        public boolean isOpr(Character token) {
            return PLUS.isOpr(token) || MINUS.isOpr(token);
        }
    };

    public abstract boolean isOpr(Character token);
}

