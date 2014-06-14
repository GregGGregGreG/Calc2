package calc.view.desktop;

import calc.view.desktop.event.CalculatorEventImpl;
import calc.view.desktop.history.AddIntoDB;
import calc.view.desktop.history.AddIntoFile;
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
        ApplicationContext.setBean("saveMemoryIntoFile", new AddIntoFile());
        ApplicationContext.setBean("saveDB", new AddIntoDB());
    }
}