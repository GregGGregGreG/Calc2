package calc.view.desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorEventImpl implements CalculatorEvent {


    @Override
    public ActionListener listenerNumberPoint() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                CalculatorView calculatorView = (CalculatorView) ApplicationContext.getInstance().getBean("calculatorView");
                calculatorView.setInputText(calculatorView.getInputText() + source.getText());
            }
        };
    }

//    public final ActionListener listenerNumberZero = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            final JButton source = (JButton) e.getSource();
//            String expression = inputField.getText();
//            if (expression.equals("0")) inputField.setText("0");
//            else inputField.setText(inputField.getText() + source.getText());
//        }
//    };
//
//    public final ActionListener listenerNumberButtons = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            CalculatorView calculatorView = (CalculatorView) ApplicationContext.getInstance().getBean("calculatorView");
//            final JButton source = (JButton) e.getSource();
//            if (calculatorView.getInputText().equals(String.valueOf(calculatorView.getMemory())) || expressionBox.getText().length() == 0) {
//                String expression = inputField.getText();
//                if (expression.equals("0")) inputField.setText(source.getText());
//                else inputField.setText(inputField.getText() + source.getText());
//            } else {
//
//                expressionBox.setText(String.valueOf(memoryCalc));
//                inputField.setText(source.getText());
//            }
//        }
//    };
//
//    public final ActionListener listenerButtonsOperators = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            final JButton source = (JButton) e.getSource();
//            if (expressionBox.getText().equals(String.valueOf(memoryCalc)) || expressionBox.getText().length() == 0) {
//                String expression = inputField.getText();
//                if (expression.equals("0")) inputField.setText("0");
//                else inputField.setText(inputField.getText() + " " + source.getText() + " ");
//
//            } else {
//                expressionBox.setText(String.valueOf(memoryCalc));
//                inputField.setText(inputField.getText() + " " + source.getText() + " ");
//            }
//        }
//    };
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
//            expressionBox.setText(inputField.getText() + " = ");
//            try {
//                double resultExpression = infixReversePolish.parser(inputField.getText());
//                int convertNumber = (int) (resultExpression);
//                if (convertNumber == resultExpression) {
//                    memoryCalc = new StringBuilder();
//                    inputField.setText(String.valueOf(convertNumber));
//                    memoryCalc.append("Ans = " + String.valueOf(convertNumber));
//                    System.out.println(memoryCalc);
//                } else {
//                    memoryCalc = new StringBuilder();
//                    inputField.setText(String.valueOf(resultExpression));
//                    memoryCalc.append("Ans = " + String.valueOf(resultExpression));
//                }
//            } catch (NullPointerException e1) {
//                inputField.setText("Invalid expression");
//                expressionBox.setText("");
//                e1.printStackTrace();
//            } catch (Exception e1) {
//                inputField.setText("Symbol is not supported");
//                expressionBox.setText("");
//                e1.printStackTrace();
//            }
//        }
//    };
//    public final ActionListener resetCalc = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            expressionBox.setText(String.valueOf(memoryCalc));
//            inputField.setText("0");
//            inputField.grabFocus();
//        }
//    };
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
}