package calc.view.desktop;

import calc.logic.CalculationUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorEventImpl implements CalculatorEvent {


    @Override
    public ActionListener listenerNumberPoint() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                String expression = calView.getInputText();
                final JButton source = (JButton) e.getSource();
                if (expression.equals("0")) calView.setInputText("0");
                else calView.setInputText(calView.getInputText() + source.getText());
            }
        };
    }

    @Override
    public ActionListener listenerNumberZero() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                final JButton source = (JButton) e.getSource();
                String expression = calView.getInputText();
                if (expression.equals("0")) calView.setInputText("0");
                else calView.setInputText(calView.getInputText() + source.getText());
            }
        };
    }

    @Override
    public ActionListener listenerNumberButtons() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                final JButton source = (JButton) e.getSource();
                String expression = calView.getInputText();
                if (calView.getInputText().equals(calView.getMemory().toString()) || calView.getExpressionText().length() == 0) {
                    if (expression.equals("0")) calView.setInputText(source.getText());
                    else calView.setInputText(calView.getInputText() + source.getText());
                } else {
                    calView.setExpressionText(String.valueOf(calView.getMemory()));
                    calView.setInputText(source.getText());
                }
            }
        };
    }

    @Override
    public ActionListener listenerButtonsOperators() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                final JButton source = (JButton) e.getSource();
                if (calView.getExpressionText().equals(calView.getMemory().toString()) || calView.getExpressionText().length() == 0) {
                    String expression = calView.getInputText();
                    if (expression.equals("0")) calView.setInputText("0");
                    else calView.setInputText(calView.getInputText() + " " + source.getText() + " ");
                } else {
                    calView.setExpressionText(String.valueOf(calView.getMemory()));
                    calView.setInputText(calView.getInputText() + " " + source.getText() + " ");
                }
            }
        };
    }

    //
//    private ActionListener listenerOpenBracket = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            final JButton source = (JButton) e.getSource();
//            String expression = inputField.getText();
//            if (expression.equals("0")) inputField.setText(source.getText());
//            else inputField.setText(inputField.getText() + " " + source.getText());
//        }
//    };
//
//    public final ActionListener listenerCloseBracket = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            final JButton source = (JButton) e.getSource();
//            String expression = inputField.getText();
//            if (expression.equals("0")) inputField.setText(source.getText());
//            else inputField.setText(inputField.getText() + " " + source.getText());
//        }
//    };
//    public final ActionListener getResultCalc = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            InfixReversePolish infixReversePolish = new InfixReversePolish();
//            CalculatorView calView = (CalculatorView) ApplicationContext.getInstance().getBean("calculatorView");
//            calView.setExpressionText(calView.getInputText() + " = ");
//            try {
//                double resultExpression = infixReversePolish.parser(calView.getInputText());
//                int convertNumber = (int) (resultExpression);
//                if (convertNumber == resultExpression) {
//                    memoryCalc = new StringBuilder();
//                    calView.setInputText(String.valueOf(convertNumber));
//                    memoryCalc.append("Ans = " + String.valueOf(convertNumber));
//                    System.out.println(memoryCalc);
//                } else {
//                    memoryCalc = new StringBuilder();
//                    inputField.setText(String.valueOf(resultExpression));
//                    memoryCalc.append("Ans = " + String.valueOf(resultExpression));
//                }
//            } catch (NullPointerException e1) {
//                calView.setInputText("Invalid expression");
//                calView.setExpressionText("");
//                e1.printStackTrace();
//            } catch (Exception e1) {
//                calView.setInputText("Symbol is not supported");
//                calView.setExpressionText("");
//                e1.printStackTrace();
//            }
//        }
//    };

    public ActionListener resetCalc() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                calView.setExpressionText(calView.getMemory());
                calView.setInputText("0");
            }
        };
    }
//    public final ActionListener getAnsResultCalc = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (inputField.getText().equals("0") && !(memoryCalc.length() == 1)) {
//                StringBuilder numberMemory = memoryCalc;
//                inputField.setText(String.valueOf(numberMemory.delete(0, 5)));
//
//            } else inputField.setText(inputField.getText() + memoryCalc.delete(0, 5));
//        }
//    };

    public AbstractAction createActionNumber(final Character operator) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                String text = String.valueOf(operator);
                String inputText = calView.getInputText();
                if (calView.getExpressionText().equals(calView.getMemory()) || calView.getExpressionText().length() == 0) {
                    String expression = inputText;
                    if (expression.equals("0")) calView.setInputText(text);
                    else if (CalculationUtil.isOperators(operator)) {
                        calView.setInputText(inputText + " " + text + " ");
                    } else if (!expression.equals("0")) {
                        calView.setInputText(inputText + text);
                    }
                } else {
                    calView.setExpressionText(calView.getMemory());
                    calView.setInputText(text);
                }
            }
        };
    }
}