package calc.view.desktop;

import calc.logic.InfixReversePolish;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Arrays;

import static calc.view.desktop.CalculatorView.setToScreenCenter;

public class Gui extends JFrame {

    private JPanel mainPanel;

    private JTextField textField1;
    private JTextField mainTetxField;

    private JButton getNumber0;
    private JButton getNumber1;
    private JButton getNumber2;
    private JButton getNumber3;
    private JButton getNumber4;
    private JButton getNumber5;
    private JButton getNumber6;
    private JButton getNumber7;
    private JButton getNumber8;
    private JButton getNumber9;
    private JButton getPointSeparator;

    private JButton getOperatorPlus;
    private JButton getOperatorMinus;
    private JButton getOperatorMultiplication;
    private JButton getOperatorDivision;

    private JButton openBracket;
    private JButton closeBracket;

    private JButton getResultButton;
    private JButton resetButton;

    private InfixReversePolish infixReversePolish = new InfixReversePolish();

    public Gui() {
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setToScreenCenter(this);
        resetButton.addActionListener(ceNumber);
        getResultButton.addActionListener(result);
        mainPanel.addKeyListener(new KeyAdapter() {
        });
        java.util.List<JButton> numberButtons = Arrays.asList(getNumber0, getNumber1, getNumber2, getNumber3,
                getNumber4, getNumber5, getNumber6, getNumber7, getNumber8, getNumber9, getPointSeparator);
        for (JButton currentButton : numberButtons) {
            currentButton.addActionListener(numberListener);
        }
        java.util.List<JButton> operatorButtons = Arrays.asList(getOperatorPlus, getOperatorMinus, getOperatorDivision, getOperatorMultiplication, openBracket, closeBracket);
        for (JButton currentButton : operatorButtons) {
            currentButton.addActionListener(operatorsListener);
        }
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
            mainTetxField.setText(mainTetxField.getText() + " " + source.getText() + " ");
        }
    };
    private ActionListener ceNumber = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainTetxField.setText("");
        }
    };


}
