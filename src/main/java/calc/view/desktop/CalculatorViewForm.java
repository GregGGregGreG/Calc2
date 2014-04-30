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
    public static final Map<Integer, Character> buttons = new HashMap<>();

    static {
        buttons.put(KeyEvent.VK_0, '0');
        buttons.put(KeyEvent.VK_1, '1');
        buttons.put(KeyEvent.VK_2, '2');
        buttons.put(KeyEvent.VK_3, '3');
        buttons.put(KeyEvent.VK_4, '4');
        buttons.put(KeyEvent.VK_5, '5');
        buttons.put(KeyEvent.VK_6, '6');
        buttons.put(KeyEvent.VK_7, '7');
        buttons.put(KeyEvent.VK_8, '8');
        buttons.put(KeyEvent.VK_9, '9');
        buttons.put(KeyEvent.VK_NUMPAD0, '0');
        buttons.put(KeyEvent.VK_NUMPAD1, '1');
        buttons.put(KeyEvent.VK_NUMPAD2, '2');
        buttons.put(KeyEvent.VK_NUMPAD3, '3');
        buttons.put(KeyEvent.VK_NUMPAD4, '4');
        buttons.put(KeyEvent.VK_NUMPAD5, '5');
        buttons.put(KeyEvent.VK_NUMPAD6, '6');
        buttons.put(KeyEvent.VK_NUMPAD7, '7');
        buttons.put(KeyEvent.VK_NUMPAD8, '8');
        buttons.put(KeyEvent.VK_NUMPAD9, '9');
        buttons.put(KeyEvent.VK_ADD, '+');
        buttons.put(KeyEvent.VK_EQUALS, '+');
        buttons.put(KeyEvent.VK_MINUS, '-');
        buttons.put(KeyEvent.VK_SUBTRACT, '-');
        buttons.put(KeyEvent.VK_MULTIPLY, '*');
        buttons.put(KeyEvent.VK_DIVIDE, '/');
    }

    public JPanel mainPanel;
    public JTextField inputField;
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
        setVisible(true);
        pack();
        setTitle("Калькулятор");
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

        List<JButton> getNumbers = Arrays.asList(getNumber1, getNumber2, getNumber3, getNumber4,
                getNumber5, getNumber6, getNumber7, getNumber8, getNumber9);
        for (JButton getNUmber : getNumbers) {
            getNUmber.addActionListener(calEvent.listenerNumberButtons());
        }
        List<JButton> getOperators = Arrays.asList(getOperatorPlus, getOperatorMinus,
                getOperatorDivision, getOperatorMultiplication);
        for (JButton getOperator : getOperators) {
            getOperator.addActionListener(calEvent.listenerButtonsOperators());
        }

        getNumber0.addActionListener(calEvent.listenerNumberZero());
        resetButton.addActionListener(calEvent.resetCalc());
//        getResult.addActionListener(getResultCalc);
//        getOpenBracket.addActionListener(listenerOpenBracket);
//        getCloseBracket.addActionListener(listenerCloseBracket);
        getPointSeparator.addActionListener(calEvent.listenerNumberPoint());
//        getPreviousResult.addActionListener(getAnsResultCalc);

        BindKey();
    }

    public void BindKey() {

        CalculatorEvent calEvent = (CalculatorEvent) ApplicationContext.getBean("calculatorEvent");
        InputMap inputFieldMap = inputField.getInputMap(JComponent.WHEN_FOCUSED);
        InputMap mainInputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        ActionMap actionMap = mainPanel.getActionMap();
        for (Map.Entry<Integer, Character> entry : buttons.entrySet()) {
            Integer entryKey = entry.getKey();
            Character operator = entry.getValue();
            String actionName = "action" +operator;
            inputFieldMap.put(KeyStroke.getKeyStroke(entryKey, 0), actionName);
            mainInputMap.put(KeyStroke.getKeyStroke(entryKey, 0), actionName);
            actionMap.put(actionName, calEvent.createActionNumber(operator));
        }

//        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "NUMPAD1");
//        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "NUMPAD1");
//        mainPanel.getActionMap().put("NUMPAD1", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
//                    String expression = inputField.getText();
//                    if (expression.equals("0")) inputField.setText("1");
//                    else inputField.setText(inputField.getText() + "1");
//                } else {
//                    expressionBox.setText(String.valueOf(memoryCalc));
//                    inputField.setText("1");
//                }
//            }
//        });

//        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "RESULT");
//        mainInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "RESULT");
//        mainPanel.getActionMap().put("RESULT", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                expressionBox.setText(inputField.getText() + " = ");
//                try {
//                    double resultExpression = infixReversePolish.parser(inputField.getText());
//                    int convertNumber = (int) (resultExpression);
//                    if (convertNumber == resultExpression) {
//                        memoryCalc = new StringBuilder();
//                        inputField.setText(String.valueOf(convertNumber));
//                        memoryCalc.append("Ans = " + String.valueOf(convertNumber));
//                        System.out.println(memoryCalc);
//                    } else {
//                        memoryCalc = new StringBuilder();
//                        inputField.setText(String.valueOf(resultExpression));
//                        memoryCalc.append("Ans = " + String.valueOf(resultExpression));
//                    }
//                } catch (NullPointerException e1) {
//                    inputField.setText("Invalid expression");
//                    expressionBox.setText("");
//                    e1.printStackTrace();
//                } catch (Exception e1) {
//                    inputField.setText("Symbol is not supported");
//                    expressionBox.setText("");
//                    e1.printStackTrace();
//
//                }
//            }
//        });
//
//
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
    public void addMemory(String text) {
        memoryCalc.append(text);
    }


}