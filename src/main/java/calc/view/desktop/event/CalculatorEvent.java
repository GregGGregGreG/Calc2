package calc.view.desktop.event;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public interface CalculatorEvent {

    AbstractAction eventsBindingButtonsKeyboard(Character param);

    ActionListener getAnsResultCalc();

    ActionListener eventsCalcViewFormButtons();

    ActionListener backspaceInputField();

    ActionListener resetCalc();

    ActionListener getResultCalc();

    KeyAdapter keyBlock();

}