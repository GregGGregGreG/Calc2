package calc.view.desktop;

import calc.logic.InfixReversePolish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static calc.view.desktop.CalculatorUtil.setToScreenCenter;


public class CalculatorViewForm extends JFrame implements CalculatorView {

    public JPanel mainPanel;

    public JTextField inputField;
    public JTextField expressionBox;

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

    private InfixReversePolish infixReversePolish = new InfixReversePolish();

    public StringBuilder memoryCalc = new StringBuilder();

    public CalculatorViewForm() {
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setToScreenCenter(this);


        CalculatorEvent calculatorEvent = (CalculatorEvent) ApplicationContext.getInstance().getBean("calculatorEvent");


        expressionBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        inputField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if ((key != '.')) {
                    e.consume();
                }
            }
        });

//        List<JButton> getNumbers = Arrays.asList(getNumber1, getNumber2, getNumber3,
//                getNumber4, getNumber5, getNumber6, getNumber7, getNumber8, getNumber9);
//        for (JButton getNUmber : getNumbers) {
//            getNUmber.addActionListener(listenerNumberButtons);
//        }
//        List<JButton> getOperators = Arrays.asList(getOperatorPlus, getOperatorMinus,
//                getOperatorDivision, getOperatorMultiplication);
//        for (JButton getOperator : getOperators) {
//            getOperator.addActionListener(listenerButtonsOperators);
//        }

//        getNumber0.addActionListener(listenerNumberZero);
//        resetButton.addActionListener(resetCalc);
//        getResult.addActionListener(getResultCalc);
//        getOpenBracket.addActionListener(listenerOpenBracket);
//        getCloseBracket.addActionListener(listenerCloseBracket);
        getPointSeparator.addActionListener(calculatorEvent.listenerNumberPoint());
//        getPreviousResult.addActionListener(getAnsResultCalc);

        BindKey();

    }

    public void BindKey() {

//        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), "NUMPAD0");
//        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), "NUMPAD0");
//        mainPanel.getActionMap().put("NUMPAD0", eventCal.bindHotKey());
//                "NUMPAD0", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
//                    String expression = inputField.getText();
//                    if (expression.equals("0")) inputField.setText("0");
//                    else inputField.setText(inputField.getText() + "0");
//                } else {
//                    expressionBox.setText(String.valueOf(memoryCalc));
//                    inputField.setText("0");
//                }
//            }
//        });


        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "NUMPAD1");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "NUMPAD1");
        mainPanel.getActionMap().put("NUMPAD1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("1");
                    else inputField.setText(inputField.getText() + "1");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("1");
                }
            }
        });

        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), "NUMPAD2");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), "NUMPAD2");
        mainPanel.getActionMap().put("NUMPAD2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("2");
                    else inputField.setText(inputField.getText() + "2");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("2");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), "NUMPAD3");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), "NUMPAD3");
        mainPanel.getActionMap().put("NUMPAD3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("3");
                    else inputField.setText(inputField.getText() + "3");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("3");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), "NUMPAD4");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), "NUMPAD4");
        mainPanel.getActionMap().put("NUMPAD4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("4");
                    else inputField.setText(inputField.getText() + "4");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("4");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), "NUMPAD5");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), "NUMPAD5");
        mainPanel.getActionMap().put("NUMPAD5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("5");
                    else inputField.setText(inputField.getText() + "5");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("5");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), "NUMPAD6");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), "NUMPAD6");
        mainPanel.getActionMap().put("NUMPAD6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("6");
                    else inputField.setText(inputField.getText() + "6");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("6");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), "NUMPAD7");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), "NUMPAD7");
        mainPanel.getActionMap().put("NUMPAD7", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("7");
                    else inputField.setText(inputField.getText() + "7");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("7");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "NUMPAD8");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "NUMPAD8");
        mainPanel.getActionMap().put("NUMPAD8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("8");
                    else inputField.setText(inputField.getText() + "8");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("8");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), "NUMPAD9");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), "NUMPAD9");
        mainPanel.getActionMap().put("NUMPAD9", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                    String expression = inputField.getText();
                    if (expression.equals("0")) inputField.setText("9");
                    else inputField.setText(inputField.getText() + "9");
                } else {
                    expressionBox.setText(String.valueOf(memoryCalc));
                    inputField.setText("9");
                }
            }
        });
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "RESULT");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "RESULT");
        mainPanel.getActionMap().put("RESULT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expressionBox.setText(inputField.getText() + " = ");
                try {
                    double resultExpression = infixReversePolish.parser(inputField.getText());
                    int convertNumber = (int) (resultExpression);
                    if (convertNumber == resultExpression) {
                        memoryCalc = new StringBuilder();
                        inputField.setText(String.valueOf(convertNumber));
                        memoryCalc.append("Ans = " + String.valueOf(convertNumber));
                        System.out.println(memoryCalc);
                    } else {
                        memoryCalc = new StringBuilder();
                        inputField.setText(String.valueOf(resultExpression));
                        memoryCalc.append("Ans = " + String.valueOf(resultExpression));
                    }
                } catch (NullPointerException e1) {
                    inputField.setText("Invalid expression");
                    expressionBox.setText("");
                    e1.printStackTrace();
                } catch (Exception e1) {
                    inputField.setText("Symbol is not supported");
                    expressionBox.setText("");
                    e1.printStackTrace();

                }
            }
        });

        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "PLUS");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "PLUS");
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "PLUS");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "PLUS");
        mainPanel.getActionMap().put("PLUS", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                            String expression = inputField.getText();
                            if (expression.equals("0")) inputField.setText("0");
                            else inputField.setText(inputField.getText() + " " + " + " + " ");

                        } else {
                            expressionBox.setText(String.valueOf(memoryCalc));
                            inputField.setText(inputField.getText() + " " + " + " + " ");
                        }
                    }
                }
        );

        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "MINUS");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "MINUS");
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "MINUS");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "MINUS");
        mainPanel.getActionMap().put("MINUS", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                            String expression = inputField.getText();
                            if (expression.equals("0")) inputField.setText("-");
                            else inputField.setText(inputField.getText() + " " + " - " + " ");

                        } else {
                            expressionBox.setText(String.valueOf(memoryCalc));
                            inputField.setText(inputField.getText() + " " + " - " + " ");
                        }
                    }
                }
        );
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "MULTIPLY");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "MULTIPLY");
        mainPanel.getActionMap().put("MULTIPLY", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                            String expression = inputField.getText();
                            if (expression.equals("0")) inputField.setText("0");
                            else inputField.setText(inputField.getText() + " " + " * " + " ");

                        } else {
                            expressionBox.setText(String.valueOf(memoryCalc));
                            inputField.setText(inputField.getText() + " " + " * " + " ");
                        }
                    }


                }
        );
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "DIVIDE");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "DIVIDE");
        mainPanel.getActionMap().put("DIVIDE", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
                            String expression = inputField.getText();
                            if (expression.equals("0")) inputField.setText("0");
                            else inputField.setText(inputField.getText() + " " + " ÷ " + " ");

                        } else {
                            expressionBox.setText(String.valueOf(memoryCalc));
                            inputField.setText(inputField.getText() + " " + " ÷ " + " ");
                        }
                    }
                }
        );
    }

    @Override
    public void setInputText(String text) {
        inputField.setText(text);
    }

    @Override
    public String getInputText() {
        return inputField.getText();
    }

    @Override
    public void setExpressionText(String text) {
        expressionBox.setText(text);
    }

    @Override
    public String getExpressionText() {
        return expressionBox.getText();
    }

    @Override
    public void setMemory(String text) {
        memoryCalc.append(text);
    }

    @Override
    public String getMemory() {
        return memoryCalc.toString();
    }
}
