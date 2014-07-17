package com.greg.calculator.logic;

import com.greg.calculator.config.BaseTest;
import com.greg.calculator.logic.parser.ParserExpression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class CalculatorUnitTest extends BaseTest {

    @Autowired
    private ParserExpression infixParser;

    @Before
    public void before() {

    }

    @Test
    public void OperatorPlus() throws Exception {
        Assert.assertTrue(infixParser.parser("2+2").equals("4"));
    }

    @Test
    public void OperatorMinus() throws Exception {
        Assert.assertTrue(infixParser.parser("23-3").equals("20"));
    }

    @Test
    public void OperatorDivision() throws Exception {
        Assert.assertTrue(infixParser.parser("5/2").equals("2.5"));
    }

    @Test
    public void OperatorMultiplication() throws Exception {
        Assert.assertTrue(infixParser.parser("2*5").equals("10"));

    }

    @Test
    public void DelSpace() throws Exception {
        Assert.assertTrue("Deleting many space", infixParser.parser("  5  / 2   ").equals("2.5"));
    }

    @Test
    public void commaAsTheSeparator() throws Exception {
        Assert.assertTrue(infixParser.parser("  5,5  / 2   ").equals("2.75"));
    }

    @Test
    public void PrioritizationOperators() throws Exception {
        Assert.assertTrue(infixParser.parser("2+3*5+6").equals("23"));
    }

    @Test
    public void Brackets() throws Exception {
        Assert.assertTrue(infixParser.parser("(20-5*3+3)/2").equals("4"));
        Assert.assertTrue(infixParser.parser("((((((((((((((((((((3+5))))))))))))-5)))))))+2").equals("5"));

    }

    @Test
    public void negativeNumber() throws Exception {
        Assert.assertTrue(infixParser.parser("-5+3").equals("-2"));
    }

    @Test
    public void exponentiation() throws Exception {
        Assert.assertTrue(infixParser.parser("2^65").equals("3.6893488147419103E19"));
    }

    @Test
    public void sqrt() throws Exception {
        Assert.assertTrue(infixParser.parser("√3").equals("1.7320508075688772"));
        Assert.assertTrue(infixParser.parser("√9+1").equals("4"));
        Assert.assertTrue(infixParser.parser("1+√9").equals("4"));
        Assert.assertTrue(infixParser.parser("√(1+√9)+1").equals("3"));
    }

    @Test
    public void cos() throws Exception {
        Assert.assertTrue(infixParser.parser("cos(5)").equals("0.28366218546322625"));
        Assert.assertTrue(infixParser.parser("cos(5)+1").equals("1.2836621854632262"));
        Assert.assertTrue(infixParser.parser("1+cos(5)").equals("1.2836621854632262"));
        Assert.assertTrue(infixParser.parser("√cos(1+cos(5))").equals("0.532169956934904"));
    }

    @Test
    public void AdditionOfFloatingPointNumbers() throws Exception {
        Assert.assertTrue(infixParser.parser("23.66+3.454").equals("27.114"));
    }

    @Test
    public void BigNumber() throws Exception {
        Assert.assertTrue(infixParser.parser("1232+3034+45-45.454+10*6646.216").equals("70727.706"));
    }

    @Test
    public void longExpression() throws Exception {
        Assert.assertTrue(infixParser.parser("((18/2)*2)+(10*10)-(8/5)+10.3-(0.3/(5-3))").equals("126.55"));
        Assert.assertTrue("multiplication and division by a negative number, and parentheses.",
                infixParser.parser("55+(((2+(2*10)-(-5))/(-10))+(88*-22)))-3").equals("-1886.7"));
    }

    @Ignore
    @Test
    public void test() throws Exception {
        Assert.assertTrue(infixParser.parser("10.3-10").equals("0.3"));
    }

    @Ignore
    @Test(expected = Exception.class)
    public void divZero() throws Exception {
        infixParser.parser("5/0");
    }

    @Test(expected = Exception.class)
    public void dontSupportSimbols() throws Exception {
        infixParser.parser("45b*4");
    }
}