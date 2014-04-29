package calc.view.desktop;

import javax.swing.*;

public class CalculatorRun implements Runnable {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new CalculatorRun());
    }

    @Override
    public void run() {
        final ApplicationContext instance = ApplicationContext.getInstance();
        instance.setBean("calculatorEvent", new CalculatorEventImpl());
        instance.setBean("calculatorView", new CalculatorViewForm());
    }
}