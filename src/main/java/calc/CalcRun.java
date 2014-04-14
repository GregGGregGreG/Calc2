package calc;

/**
 * Created by GREG on 03.03.14.
 */
public class CalcRun {
    public static void main(String[] args) {
        String expression = "5-(((2*3+2)/2)-15)";
        InfixToRpn infixToRpn = new InfixToRpn();
        PolishEvaluator evaluator = new PolishEvaluator();
        System.out.println(evaluator.evaluator(infixToRpn.parser(expression)));

    }
}
