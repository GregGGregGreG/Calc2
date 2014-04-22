import calc.InfixReversePolish;
import calc.PolishEvaluator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCalculator {
    private InfixReversePolish infixReversePolish;
    private PolishEvaluator evaluator;

    @Before
    public void before() {
        infixReversePolish = new InfixReversePolish();
        evaluator = new PolishEvaluator();
    }

    @Test
    public void testPlus() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("2+2")) == 4);
    }

    @Test
    public void testMinus() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("23-3")) == 20);
    }

    @Test
    public void testAdditionOfFloatingPointNumbers() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("23.66+3.454")) == 27.114);
    }

    @Test
    public void test() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("10.3-10")) == 0.3);
    }

    @Test
    public void testDivision() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("5/2")) == 2.5);
    }

    @Test
    public void testDelSpace() throws Exception {
        Assert.assertTrue("Deleting many space", evaluator.evaluator(infixReversePolish.parser("  5  / 2   ")) == 2.5);
    }

    @Test
    public void commaAsTheSeparator() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("  5,5  / 2   "))==2.75);
    }

    @Test
    public void multiplicationTest() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("2*5")) == 10);

    }

    @Test
    public void longExpression() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("((18/2)*2)+(10*10)-(8/5)+10.3-(0.3/(5-3))")) == 126.55);
        Assert.assertTrue("multiplication and division by a negative number, and parentheses.",evaluator.evaluator(infixReversePolish.parser("55+(((2+(2*10)-(-5))/(-10))+(88*-22)))-3")) == -1886.7);
    }

    @Test
    public void negativeNumber() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("-5+3")) == -2);
    }

    @Test(expected = Exception.class)
    public void divZero() throws Exception {
        evaluator.evaluator(infixReversePolish.parser("5/0"));
    }


    @Test
    public void testPrioritizationOperators() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("2+3*5+6")) == 23);
    }

    @Test
    public void testBrackets() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("(20-5*3+3)/2")) == 4);
    }

    @Test
    public void testBigNumber() throws Exception {
        Assert.assertTrue(evaluator.evaluator(infixReversePolish.parser("1232+3034+45-45.454+10*6646.216")) == 70727.706);
    }
}