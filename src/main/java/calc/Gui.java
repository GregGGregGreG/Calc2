package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/**
 * Created by GREG on 22.04.2014.
 */
public class Gui extends JFrame {
    //InfixReversePolish infixReversePolish = new InfixReversePolish();
    private JPanel panel1;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton button4;
    private JTextField textField1;
    private JTextField textField2;


    public Gui() {
        setContentPane(panel1);
        setVisible(true);
        pack();
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setToScreenCenter(this);
//        resultButton.addActionListener(result);
        panel1.addKeyListener(new KeyAdapter() {
        });
    }

    public static void setToScreenCenter(Component component) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension componentSize = component.getSize();
        component.setBounds(screenSize.width / 2 - componentSize.width / 2,
                screenSize.height / 2 - componentSize.height / 2,
                component.getWidth(),
                component.getHeight());
    }

    private final ActionListener result = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    };
}
