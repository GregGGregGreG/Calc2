package calc.logic;

import calc.view.desktop.CalculatorView;

import javax.swing.*;

public class CalcRun implements Runnable {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new CalcRun());
    }

    @Override
    public void run() {
        new CalculatorView();
    }
}