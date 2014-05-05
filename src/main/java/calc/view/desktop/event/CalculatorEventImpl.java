package calc.view.desktop.event;

import calc.exceptions.ExceptionInfixReversPolish;
import calc.exceptions.ExceptionPolishEvaluator;
import calc.logic.CalculationUtil;
import calc.logic.InfixReversePolish;
import calc.view.desktop.ApplicationContext;
import calc.view.desktop.view.CalculatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CalculatorEventImpl implements CalculatorEvent {
    private boolean addOperator = false;
    private boolean addPoint = true;
    private boolean negativNumber = true;
    private int openBracket = 0;
    private int closeBracket = 0;


    @Override
    public final KeyAdapter keyBlock() {
        return new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                Character key = e.getKeyChar();
                if (!(key == '.'))
                    e.consume();
            }
        };
    }

    @Override
    public final AbstractAction backspaceInputField() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backspace();
            }
        };
    }

    @Override
    public final ActionListener resetCalc() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        };
    }

    @Override
    public final AbstractAction getResultCalc() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result();
            }
        };
    }

    @Override
    public final ActionListener getAnsResultCalc() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ansResultCalc();
            }
        };
    }

    @Override
    public final ActionListener eventsCalcViewFormButtons() {
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
    public final AbstractAction eventsBindingButtonsKeyboard(final Character operator) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionNumberAndOperator(operator);
            }
        };
    }

    private final void actionNumberAndOperator(Character operator) {
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

    private final void point(CalculatorView calcView, String text) {
        if (addPoint) {
            calcView.setInputText(calcView.getInputText() + text);
            addPoint = false;
        }
    }

    private final void openBracket(CalculatorView calcView, String text) {
        if (calcView.getInputText().equals("0")) calcView.setInputText(text);
        else if (addOperator == false) calcView.setInputText(calcView.getInputText() + " " + text);
        openBracket++;
    }

    private final void closeBracket(CalculatorView calcView, String text) {
        if (calcView.getInputText().equals("0")) return;
        else if (!(closeBracket == openBracket)) {
            calcView.setInputText(calcView.getInputText() + text + " ");
            openBracket--;
        }
    }

    private final void operator(CalculatorView calcView, String text) {
        if (addOperator) {
            calcView.setInputText(calcView.getInputText() + " " + text + " ");
            addOperator = false;
            addPoint = true;
        } else if (text.equals("-") && negativNumber) {
            if (calcView.getInputText().equals("0")) {
                calcView.setInputText(text);
            } else {
                calcView.setInputText(calcView.getInputText() + text);
            }
            negativNumber = false;
        }
    }

    private final void digit(CalculatorView calcView, String text, String inputText) {
        if (inputText.equals("0")) calcView.setInputText(text);
        else calcView.setInputText(calcView.getInputText() + text);
        addOperator = true;
        addPoint = true;
        negativNumber = true;
    }

    private final void result() {
        CalculatorView calcView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        if (calcView.getInputText().length() == 1) return;
        if (!(calcView.getMemory().length() == 0) && calcView.getInputText().equals(calcView.getMemory().substring(6))) {
            return;
        }
        calcView.setExpressionText(calcView.getInputText() + " = ");
        try {
            double resultExpression = InfixReversePolish.parser(calcView.getInputText());
            int convertNumber = (int) (resultExpression);
            if (convertNumber == resultExpression) {
                calcView.setInputText(String.valueOf(convertNumber));
                calcView.setMemory("Ans = " + String.valueOf(convertNumber));
                System.out.println(calcView.getMemory());
            } else {
                calcView.setInputText(String.valueOf(resultExpression));
                calcView.setMemory("Ans = " + String.valueOf(resultExpression));
            }
        } catch (ExceptionInfixReversPolish e1) {
            calcView.setExpressionText("Symbol is not supported");
        } catch (ExceptionPolishEvaluator e1) {
            calcView.setExpressionText("Invalid expression");
        } finally {
            addOperator = true;
        }
    }

    private final void ansResultCalc() {
        CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        if (!(calView.getMemory().length() == 0) && addOperator == false) {
            calView.setInputText(calView.getInputText() + calView.getMemory().substring(6));
            addOperator = true;
        }
    }

    private final void reset() {
        CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        calView.setExpressionText(calView.getMemory());
        calView.setInputText("0");
        addOperator = false;
        negativNumber = true;
        openBracket = 0;

    }

    private final void backspace() {
        CalculatorView calcView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        if (calcView.getInputText().length() == 0) return;
        calcView.setInputText(calcView.getInputText().substring(0, calcView.getInputText().length() - 1));
        addOperator = true;
        negativNumber = true;
    }
}