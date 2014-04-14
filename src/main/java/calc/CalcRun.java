package calc;

/**
 * Created by GREG on 03.03.14.
 */
public class CalcRun {
    public static void main(String[] args) {
        String expression = "2+2";
        InfixToRpn infixToRpn = new InfixToRpn();
        PolishEvaluator evaluator = new PolishEvaluator();
        System.out.println(evaluator.evaluator(infixToRpn.parser(expression)));

    }
}
