package calc.view.desktop;

import calc.exception.ExceptionInfixReversPolish;
import calc.exception.ExceptionPolishEvaluator;
import calc.logic.CalculationUtil;
import calc.logic.InfixReversePolish;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CalculatorEventImpl implements CalculatorEvent {
    private boolean addOperator = false;
    private boolean addPoint = true;

    public KeyAdapter keyBlock() {
        return new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                Character key = e.getKeyChar();
                if (!(key == '.'))

                    e.consume();
                addPoint = false;
            }

        };
    }

    public ActionListener resetCalc() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
                calView.setExpressionText(calView.getMemory());
                calView.setInputText("0");
                addOperator = false;
            }
        };
    }

    @Override
    public AbstractAction getResultCalc() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result();
            }
        };
    }

    //    public final ActionListener getAnsResultCalc = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
//            if (calView.getInputText().equals("0") && !(memoryCalc.length() == 1)) {
//                StringBuilder numberMemory = memoryCalc;
//                inputField.setText(String.valueOf(numberMemory.delete(0, 5)));
//
//            } else inputField.setText(inputField.getText() + memoryCalc.delete(0, 5));
//        }
//    };

    @Override
    public ActionListener eventsCalcViewFormButtons() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                String operator = source.getText();
                Character chars = operator.charAt(0);
                actionNumberAndOperator(chars);
            }
        };
    }

    @Override
    public AbstractAction eventsBindingButtonsKeyboard(final Character operator) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionNumberAndOperator(operator);
            }
        };
    }

    private void actionNumberAndOperator(Character operator) {
        CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        String text = String.valueOf(operator);
        String inputText = calView.getInputText();
        if (calView.getExpressionText().equals(calView.getMemory()) || calView.getExpressionText().length() == 0) {
            if (Character.isDigit(operator)) digit(calView, text, inputText);
            else if (CalculationUtil.isOperators(operator)) operator(calView, text);
            else if (CalculationUtil.isOpenBracket(operator)) openBracket(calView, text);
            else if (CalculationUtil.isCloseBracket(operator)) closeBracket(calView, text);
            else if (operator == '.') point(calView, text);
        } else {
            calView.setExpressionText(calView.getMemory());
            if (CalculationUtil.isOperators(operator)) operator(calView, text);
            else calView.setInputText(text);
        }
    }

    private void point(CalculatorView calcView, String text) {
        if (addPoint) {
            calcView.setInputText(calcView.getInputText() + text);
            addPoint = false;
        }
    }

    private void openBracket(CalculatorView calcView, String text) {
        calcView.setInputText(calcView.getInputText() + " " + text);
    }

    private void closeBracket(CalculatorView calcView, String text) {
        calcView.setInputText(calcView.getInputText() + text + " ");
    }

    private void operator(CalculatorView calcView, String text) {
        if (addOperator) {
            calcView.setInputText(calcView.getInputText() + " " + text + " ");
            addOperator = false;
            addPoint = true;
        }
    }

    private void digit(CalculatorView calcView, String text, String inputText) {
        if (inputText.equals("0")) calcView.setInputText(text);
        else calcView.setInputText(calcView.getInputText() + text);
        addOperator = true;
        addPoint = true;
    }

    private void result() {

        CalculatorView calcView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        if (calcView.getInputText().length() == 1) {
            return;
        }
        if (calcView.getInputText().equals(calcView.getMemory())) {
            return;
        }
        calcView.setExpressionText(calcView.getInputText() + " = ");
        try {
            double resultExpression = InfixReversePolish.parser(calcView.getInputText());
            int convertNumber = (int) (resultExpression);
            if (convertNumber == resultExpression) {
                calcView.cleanMemory();
                calcView.setInputText(String.valueOf(convertNumber));
                calcView.setMemory("Ans = " + String.valueOf(convertNumber));
                System.out.println(calcView.getMemory());
            } else {
                calcView.cleanMemory();
                calcView.setInputText(String.valueOf(resultExpression));
                calcView.setMemory("Ans = " + String.valueOf(resultExpression));
            }
        } catch (ExceptionInfixReversPolish e1) {
            calcView.setExpressionText("Symbol is not supported");
            e1.printStackTrace();
        } catch (ExceptionPolishEvaluator e1) {
            calcView.setExpressionText("Invalid expression");
            e1.printStackTrace();
        }
    }
}