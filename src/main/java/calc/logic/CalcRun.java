package calc.logic;

import calc.view.desktop.Gui;

import javax.swing.*;

/**
 * Created by GREG on 03.03.14.
 */
public class CalcRun implements Runnable{
    public static void main(String[] args) throws Exception {
        String expression = "55+(((2+(2*10)-(-5))/(-10))+(88*-22)))-3";
        //String expression = "-5/0";
        //InfixReversePolish infixReversePolish = new InfixReversePolish();
        //System.out.println("Результат: "+expression+" = "+infixReversePolish.parser(expression));
        SwingUtilities.invokeLater(new CalcRun());
    }

    @Override
    public void run() {
        new Gui();
    }
}


