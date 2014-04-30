package calc.view.desktop;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by GREG on 29.04.2014.
 */
public interface CalculatorEvent {

    ActionListener listenerNumberPoint();

    ActionListener listenerNumberZero();

    ActionListener listenerNumberButtons();

    ActionListener listenerButtonsOperators();

    ActionListener resetCalc();

    AbstractAction createActionNumber(Character param);

}
