package calc.view.desktop;

import java.awt.*;

public class CalculatorView {
    public static void setToScreenCenter(Component component) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension componentSize = component.getSize();
        component.setBounds(screenSize.width / 2 - componentSize.width / 2,
                screenSize.height / 2 - componentSize.height / 2,
                component.getWidth(),
                component.getHeight());
    }
}
