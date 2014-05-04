package calc.view.desktop;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public interface CalculatorEvent {

    ActionListener eventsCalcViewFormButtons();

    AbstractAction eventsBindingButtonsKeyboard(Character param);

    ActionListener resetCalc();

    ActionListener getResultCalc();

    KeyAdapter keyBlock();

}