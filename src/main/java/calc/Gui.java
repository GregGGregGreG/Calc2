package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Arrays;

/**
 * Created by GREG on 22.04.2014.
 */
public class Gui extends JFrame {
    InfixReversePolish infixReversePolish = new InfixReversePolish();
    private JPanel panel1;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JTextField textField1;
    private JTextField mainTetxField;
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
    private JButton resultButton;


    public Gui() {
        setContentPane(panel1);
        setVisible(true);
        pack();
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setToScreenCenter(this);
        CE.addActionListener(ceNumber);
        resultButton.addActionListener(result);
        panel1.addKeyListener(new KeyAdapter() {
        });
        java.util.List<JButton> numberButtons = Arrays.asList(a0Button, a1Button, a2Button, a3Button,
                a4Button, a5Button, a6Button, a7Button, a8Button, a9Button, pointButton);
        for (JButton currentButton : numberButtons) {
            currentButton.addActionListener(numberListener);
        }
        java.util.List<JButton> operatorButtons = Arrays.asList(plus, minus, division, multiplication,openBrecket,closeBracket);
        for (JButton currentButton : operatorButtons) {
            currentButton.addActionListener(operatorsListener);
        }
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

    private ActionListener result = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField1.setText(mainTetxField.getText() + " = ");
            mainTetxField.setText(infixReversePolish.parser(mainTetxField.getText()));
        }
    };
    private ActionListener numberListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            mainTetxField.setText(mainTetxField.getText() + source.getText());
        }
    };
    private ActionListener operatorsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            mainTetxField.setText(mainTetxField.getText()+" "+source.getText()+" ");
        }
    };
    private ActionListener ceNumber=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainTetxField.setText("");
        }
    };


}
