package calc.view.desktop;

import calc.exception.ExceptionInfixReversPolish;
import calc.exception.ExceptionPolishEvaluator;
import calc.logic.CalculationUtil;
import calc.logic.InfixReversePolish;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorEventImpl implements CalculatorEvent {


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
//            CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
//            if (calView.getInputText().equals("0") && !(memoryCalc.length() == 1)) {
//                StringBuilder numberMemory = memoryCalc;
//                inputField.setText(String.valueOf(numberMemory.delete(0, 5)));
//
//            } else inputField.setText(inputField.getText() + memoryCalc.delete(0, 5));
//        }
//    };
    @Override
    public ActionListener listenerNumberAndButtonsOperators() {
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

    public AbstractAction createActionNumber(final Character operator) {
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
            if (inputText.equals("0") && !operator.equals('=')) calView.setInputText(text);
            if (inputText.equals("0") && operator.equals('=')) calView.setInputText("0");
            else if (Character.isDigit(operator)) digit(calView, text);
            else if (CalculationUtil.isOperators(operator)) operator(calView, text);
            else if (CalculationUtil.isOpenBracket(operator)) openBracket(calView, text);
            else if (CalculationUtil.isCloseBracket(operator)) closeBracket(calView, text);
            else if (operator.equals('=')) result(calView);
        } else {
            calView.setExpressionText(calView.getMemory());
            calView.setInputText(text);
        }
    }

    private void result(CalculatorView calcView) {

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

    private void openBracket(CalculatorView calcView, String text) {
        calcView.setInputText(calcView.getInputText() + " " + text);
    }

    private void closeBracket(CalculatorView calcView, String text) {
        calcView.setInputText(calcView.getInputText() + text + " ");
    }

    private void operator(CalculatorView calcView, String text) {
        calcView.setInputText(calcView.getInputText() + " " + text + " ");
    }

    private void digit(CalculatorView calcView, String text) {
        calcView.setInputText(calcView.getInputText() + text);
    }
}