package calculator;

import calculator.view.desktop.ApplicationContext;
import calculator.view.desktop.event.CalculatorEventImpl;
import calculator.view.desktop.history.AddIntoFile;
import calculator.view.desktop.history.AddIntoXML;
import calculator.view.desktop.view.CalculatorViewForm;

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
//        ApplicationContext.setBean("saveDB", new AddIntoDB());
        ApplicationContext.setBean("saveXML", new AddIntoXML());

    }
}