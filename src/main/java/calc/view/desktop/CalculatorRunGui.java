package calc.view.desktop;

import calc.view.desktop.event.CalculatorEventImpl;
import calc.view.desktop.view.CalculatorViewForm;

import javax.swing.*;

public class CalculatorRunGui implements Runnable {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new CalculatorRunGui());
    }

    @Override
    public void run() {
        ApplicationContext.setBean("calculatorEvent", new CalculatorEventImpl());
        ApplicationContext.setBean("calculatorView", new CalculatorViewForm());
    }
}