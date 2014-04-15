package calc;

/**
 * Created by GREG on 03.03.14.
 */
public class CalcRun {
    public static void main(String[] args) throws Exception {
        //String expression = "5-(((2*3+2)/2)-15)";
        String expression = "1 +2";
        InfixReversePolish infixReversePolish = new InfixReversePolish();
        PolishEvaluator evaluator = new PolishEvaluator();
        System.out.println("Результат: "+expression+" = "+evaluator.evaluator(infixReversePolish.parser(expression)));

    }
}

//18+100-1,6 +10,3- 0,15
