package calc.view.desktop;

import calc.logic.InfixReversePolish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import static calc.view.desktop.CalculatorUtil.setToScreenCenter;

public class CalculatorView extends JFrame {

    private JPanel mainPanel;

    private JFormattedTextField inputField;
    private JTextField expressionBox;

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
    private JButton resetButton;
    private JPanel panleInputField;

    private InfixReversePolish infixReversePolish = new InfixReversePolish();

    private Double ans;

    public CalculatorView() {
        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        expressionBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        inputField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        setToScreenCenter(this);


        List<JButton> numberButtons = Arrays.asList(getNumber1, getNumber2, getNumber3,
                getNumber4, getNumber5, getNumber6, getNumber7, getNumber8, getNumber9);
        for (JButton currentButton : numberButtons) {
            currentButton.addActionListener(listenerNumberButtons);
        }
        List<JButton> operatorButtons = Arrays.asList(getOperatorPlus, getOperatorMinus,
                getOperatorDivision, getOperatorMultiplication);
        for (JButton currentButton : operatorButtons) {
            currentButton.addActionListener(listenerButtonsOperators);
        }
        getNumber0.addActionListener(listenerNumberZero);
        resetButton.addActionListener(resetCalc);
        getResult.addActionListener(getResultCalc);
        getOpenBracket.addActionListener(listenerOpenBracket);
        getCloseBracket.addActionListener(listenerCloseBracket);
        getPointSeparator.addActionListener(listenerNumberPoint);
        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if (!Character.isDigit(key) && (key != '.')) {
                    e.consume();
                }
            }
        });
        BindKey();
    }

    public void BindKey() {

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
                        inputField.setText(String.valueOf(convertNumber));
                    } else {
                        inputField.setText(String.valueOf(resultExpression));
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
                        inputField.setText(inputField.getText() + " + ");
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
                        inputField.setText(inputField.getText() + " - ");
                    }
                }
        );
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "MULTIPLY");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "MULTIPLY");
        mainPanel.getActionMap().put("MULTIPLY", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inputField.setText(inputField.getText() + " × ");
                    }
                }
        );
        inputField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "DIVIDE");
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "DIVIDE");
        mainPanel.getActionMap().put("DIVIDE", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inputField.setText(inputField.getText() + " ÷ ");
                    }
                }
        );
    }

    private ActionListener listenerNumberPoint = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            inputField.setText(inputField.getText() + source.getText());
        }
    };private ActionListener listenerNumberZero = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            String expression = inputField.getText();
            if (expression.equals("0")) inputField.setText("0");
            else inputField.setText(inputField.getText() + source.getText());
        }
    };
    private ActionListener listenerNumberButtons = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            String expression = inputField.getText();
            if (expression.equals("0")) inputField.setText(source.getText());
            else inputField.setText(inputField.getText() + source.getText());
        }
    };
    private ActionListener listenerButtonsOperators = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            inputField.setText(inputField.getText() + " " + source.getText() + " ");
        }
    };
    private ActionListener listenerOpenBracket = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            inputField.setText(inputField.getText() + " " + source.getText());
        }
    };
    private ActionListener listenerCloseBracket = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton source = (JButton) e.getSource();
            inputField.setText(inputField.getText() + source.getText() + " ");
        }
    };
    private ActionListener getResultCalc = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            expressionBox.setText(inputField.getText() + " = ");
            try {
                double resultExpression = infixReversePolish.parser(inputField.getText());
                int convertNumber = (int) (resultExpression);
                if (convertNumber == resultExpression) {
                    inputField.setText(String.valueOf(convertNumber));
                } else {
                    inputField.setText(String.valueOf(resultExpression));
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
    };
    private ActionListener resetCalc = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputField.setText("0");
            inputField.grabFocus();
        }
    };
}
