package calc.view.desktop.event;

import calc.exceptions.InfixReversPolishException;
import calc.exceptions.PolishEvaluatorException;
import calc.logic.EOperator;
import calc.logic.InfixReversePolish;
import calc.model.History;
import calc.view.desktop.ApplicationContext;
import calc.view.desktop.history.AddIntoFile;
import calc.view.desktop.view.CalculatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;


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
                Character operator = e.getActionCommand().charAt(0);
                actionNumberAndOperator(operator);
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

    private void actionNumberAndOperator(Character operator) {
        CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        String text = String.valueOf(operator);
        String inputText = calView.getInputText();
        String expressionBox = calView.getExpressionText();
        String memory = calView.getMemory();
        if (expressionBox.equals(memory) || expressionBox.length() == 0) {
            if (Character.isDigit(operator)) digit(calView, text, inputText);
            else if (EOperator.IS_OPERATOR.isOpr(operator)) operator(calView, text, inputText);
            else if (EOperator.OPENBRACKET.isOpr(operator)) openBracket(calView, text, inputText);
            else if (EOperator.CLOSEBRACKET.isOpr(operator)) closeBracket(calView, text, inputText);
            else if (operator == '.') point(calView, text, inputText);
        } else {
            calView.setExpressionText(memory);
            if (EOperator.IS_OPERATOR.isOpr(operator)) operator(calView, text, inputText);
            else calView.setInputText(text);
        }
    }

    private void point(CalculatorView calcView, String text, String inputText) {
        if (addPoint) {
            calcView.setInputText(inputText + text);
            addPoint = false;
        }
    }

    private void openBracket(CalculatorView calcView, String text, String inputText) {
        if (inputText.equals("0")) {
            calcView.setInputText(text);
            openBracket++;
        } else if (addOperator == false) {
            calcView.setInputText(inputText + " " + text);
            openBracket++;
        }
    }

    private void closeBracket(CalculatorView calcView, String text, String inputText) {
        if (inputText.equals("0")) return;
        else if (!(closeBracket == openBracket) && addOperator == true) {
            calcView.setInputText(inputText + text + " ");
            openBracket--;
        }
    }

    private void operator(CalculatorView calcView, String text, String inputText) {
        if (addOperator) {
            calcView.setInputText(inputText + " " + text + " ");
            addOperator = false;
            addPoint = true;
        } else if (text.equals("-") && negativNumber) {
            if (inputText.equals("0")) {
                calcView.setInputText(text);
            } else {
                calcView.setInputText(inputText + text);
            }
            negativNumber = false;
        } else if (text.equals("√")) {
            if (inputText.equals("0")) {
                calcView.setInputText(text);
            } else {
                calcView.setInputText(inputText + text);
            }
        }
    }

    private void digit(CalculatorView calcView, String text, String inputText) {
        if (inputText.equals("0")) calcView.setInputText(text);
        else calcView.setInputText(inputText + text);
        addOperator = true;
        addPoint = true;
        negativNumber = true;
    }

    private void result() {
        CalculatorView calcView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        AddIntoFile intoFile = (AddIntoFile) ApplicationContext.getBean("saveMemoryIntoFile");
        String inputText = calcView.getInputText();
        String memory = calcView.getMemory();
        if (inputText.length() == 1) return;
        if (!(memory.length() == 0) && inputText.equals(memory.substring(6))) return;
        calcView.setExpressionText(inputText + " = ");
        try {
            String result = InfixReversePolish.parser(inputText);
            calcView.setInputText(result);
            calcView.setMemory("Ans = " + result);
            calcView.addDataHistoryTable(new History(new Date(), inputText + " =", result));
            intoFile.addHistory(calcView.getMyData().get(calcView.getMyData().size() - 1).getCurrentHistory());
        } catch (InfixReversPolishException e1) {
            calcView.setExpressionText("Symbol is not supported");
        } catch (PolishEvaluatorException e1) {
            calcView.setExpressionText("Invalid expression");
        }
    }

    private void ansResultCalc() {
        CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        if (!(calView.getMemory().length() == 0) && addOperator == false) {
            calView.setInputText(calView.getInputText() + calView.getMemory().substring(6));
            addOperator = true;
            addPoint = true;
            negativNumber = true;
        }
    }

    private void reset() {
        CalculatorView calView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        calView.setExpressionText(calView.getMemory());
        calView.setInputText("0");
        addOperator = false;
        negativNumber = true;
        openBracket = 0;
    }

    private void backspace() {
        CalculatorView calcView = (CalculatorView) ApplicationContext.getBean("calculatorView");
        String inputText = calcView.getInputText();
        if (inputText.length() == 0) return;
        calcView.setInputText(inputText.substring(0, inputText.length() - 1));
        addOperator = true;
        negativNumber = true;
        openBracket++;
    }
}