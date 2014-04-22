package calc;

import javax.swing.*;
import java.awt.*;
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
    private JTextField textField1;
    private JTextField textField2;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton pointButton;
    private JButton openBrecket;
    private JButton closeBracket;
    private JButton division;
    private JButton minus;
    private JButton plus;
    private JButton CE;
    private JButton multiplication;
    private JButton result;


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



}
