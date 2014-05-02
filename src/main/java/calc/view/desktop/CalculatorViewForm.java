package calc.view.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static calc.view.desktop.CalculatorUtil.setToScreenCenter;


public class CalculatorViewForm extends JFrame implements CalculatorView, Serializable {
    public static final Map<Integer, Character> bindButtons = new HashMap<>();

    static {
        bindButtons.put(KeyEvent.VK_0, '0');
        bindButtons.put(KeyEvent.VK_1, '1');
        bindButtons.put(KeyEvent.VK_2, '2');
        bindButtons.put(KeyEvent.VK_3, '3');
        bindButtons.put(KeyEvent.VK_4, '4');
        bindButtons.put(KeyEvent.VK_5, '5');
        bindButtons.put(KeyEvent.VK_6, '6');
        bindButtons.put(KeyEvent.VK_7, '7');
        bindButtons.put(KeyEvent.VK_8, '8');
        bindButtons.put(KeyEvent.VK_9, '9');
        bindButtons.put(KeyEvent.VK_NUMPAD0, '0');
        bindButtons.put(KeyEvent.VK_NUMPAD1, '1');
        bindButtons.put(KeyEvent.VK_NUMPAD2, '2');
        bindButtons.put(KeyEvent.VK_NUMPAD3, '3');
        bindButtons.put(KeyEvent.VK_NUMPAD4, '4');
        bindButtons.put(KeyEvent.VK_NUMPAD5, '5');
        bindButtons.put(KeyEvent.VK_NUMPAD6, '6');
        bindButtons.put(KeyEvent.VK_NUMPAD7, '7');
        bindButtons.put(KeyEvent.VK_NUMPAD8, '8');
        bindButtons.put(KeyEvent.VK_NUMPAD9, '9');
        bindButtons.put(KeyEvent.VK_ADD, '+');
        bindButtons.put(KeyEvent.VK_EQUALS, '+');
        bindButtons.put(KeyEvent.VK_MINUS, '-');
        bindButtons.put(KeyEvent.VK_SUBTRACT, '-');
        bindButtons.put(KeyEvent.VK_MULTIPLY, '*');
        bindButtons.put(KeyEvent.VK_DIVIDE, '/');
        bindButtons.put(KeyEvent.VK_ENTER,'=');
    }

    public JPanel mainPanel;
    public JFormattedTextField inputField;
    public JTextField expressionBox;
    public StringBuilder memoryCalc = new StringBuilder();
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
    private JButton getOpenBracket;
    private JButton getCloseBracket;
    private JButton getResult;
    private JButton getPreviousResult;
    private JButton resetButton;

    public CalculatorViewForm() {
        setContentPane(mainPanel);
        setTitle("Калькулятор");
        setVisible(true);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        expressionBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        inputField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        setToScreenCenter(this);

        CalculatorEvent calEvent = (CalculatorEvent) ApplicationContext.getBean("calculatorEvent");

        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if ((key != '.')) {
                    e.consume();
                }
            }
        });

        List<JButton> getNumbers = Arrays.asList(getNumber0, getNumber1, getNumber2, getNumber3, getNumber4,
                getNumber5, getNumber6, getNumber7, getNumber8, getNumber9,
                getOperatorPlus, getOperatorMinus, getOperatorDivision, getOperatorMultiplication, getPointSeparator,
                getOpenBracket, getCloseBracket,getResult);
        for (JButton getNUmber : getNumbers) {
            getNUmber.addActionListener(calEvent.listenerNumberAndButtonsOperators());
        }

        resetButton.addActionListener(calEvent.resetCalc());
        //getResult.addActionListener(calEvent.getResultCalc());
        //   getPreviousResult.addActionListener(getAnsResultCalc);

        BindKey();
    }

    public void BindKey() {

        CalculatorEvent calEvent = (CalculatorEvent) ApplicationContext.getBean("calculatorEvent");
        InputMap inputFieldMap = inputField.getInputMap(JComponent.WHEN_FOCUSED);
        InputMap mainInputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        ActionMap actionMap = mainPanel.getActionMap();
        for (Map.Entry<Integer, Character> entry : bindButtons.entrySet()) {
            Integer entryKey = entry.getKey();
            Character operator = entry.getValue();
            String actionName = "action" + operator;
            inputFieldMap.put(KeyStroke.getKeyStroke(entryKey, 0), actionName);
            mainInputMap.put(KeyStroke.getKeyStroke(entryKey, 0), actionName);
            actionMap.put(actionName, calEvent.createActionNumber(operator));
        }
    }


    @Override
    public String getInputText() {
        return inputField.getText();
    }

    @Override
    public void setInputText(String text) {
        inputField.setText(text);
        inputField.grabFocus();
    }

    @Override
    public String getExpressionText() {
        return expressionBox.getText();
    }

    @Override
    public void setExpressionText(String text) {
        expressionBox.setText(text);
    }

    @Override
    public String getMemory() {
        return memoryCalc.toString();
    }

    @Override
    public void setMemory(String text) {
        memoryCalc.append(text);
    }
    @Override
    public void cleanMemory() {
        memoryCalc = new StringBuilder();
    }
}