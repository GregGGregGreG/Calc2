package calc.logic;

import calc.exceptions.InfixReversPolishException;
import calc.exceptions.PolishEvaluatorException;

import java.util.Scanner;

public class CalculatorRunConsole {

    public static void main(String[] args) throws PolishEvaluatorException, InfixReversPolishException {
        for (int x = 0; x < 1; x--)
            getExpr();

    }

    private static void getExpr() throws PolishEvaluatorException, InfixReversPolishException {
        System.out.println("Enter Expression");
        String inputExpression;
        Scanner in = new Scanner(System.in);
        inputExpression = in.nextLine();
        System.out.println("Result : " + InfixReversePolish.parser(inputExpression));
    }
}
