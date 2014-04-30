package calc.view.desktop;

import javax.swing.*;

public class CalculatorRun implements Runnable {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new CalculatorRun());
    }

    @Override
    public void run() {
        ApplicationContext.setBean("calculatorEvent", new CalculatorEventImpl());
        ApplicationContext.setBean("calculatorView", new CalculatorViewForm());
    }
}