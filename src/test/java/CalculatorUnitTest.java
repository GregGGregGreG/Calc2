import calc.logic.InfixReversePolish;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorUnitTest {
    private InfixReversePolish infixReversePolish;

    @Before
    public void before() {
        infixReversePolish = new InfixReversePolish();
    }

    @Test
    public void testOperatorPlus() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("2+2").equals("4"));
    }

    @Test
    public void testOperatorMinus() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("23-3").equals("20"));
    }

    @Test
    public void testOperatorDivision() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("5/2").equals("2.5"));
    }

    @Test
    public void testOperatorMultiplication() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("2*5").equals("10"));

    }

    @Test
    public void testDelSpace() throws Exception {
        Assert.assertTrue("Deleting many space", infixReversePolish.parser("  5  / 2   ").equals("2.5"));
    }

    @Test
    public void commaAsTheSeparator() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("  5,5  / 2   ").equals("2.75"));
    }

    @Test
    public void testPrioritizationOperators() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("2+3*5+6").equals("23"));
    }

    @Test
    public void testBrackets() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("(20-5*3+3)/2").equals("4"));
    }

    @Test
    public void negativeNumber() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("-5+3").equals("-2"));
    }

    @Test
    public void testAdditionOfFloatingPointNumbers() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("23.66+3.454").equals("27.114"));
    }

    @Test
    public void testBigNumber() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("1232+3034+45-45.454+10*6646.216").equals("70727.706"));
    }

    @Test
    public void longExpression() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("((18/2)*2)+(10*10)-(8/5)+10.3-(0.3/(5-3))").equals("126.55"));
        Assert.assertTrue("multiplication and division by a negative number, and parentheses.",
                infixReversePolish.parser("55+(((2+(2*10)-(-5))/(-10))+(88*-22)))-3").equals("-1886.7"));
    }

    @Test
    public void test() throws Exception {
        Assert.assertTrue(infixReversePolish.parser("10.3-10").equals("0.3"));
    }

    @Test(expected = Exception.class)
    public void divZero() throws Exception {
        infixReversePolish.parser("5/0");
    }

}