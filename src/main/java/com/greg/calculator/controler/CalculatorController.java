package com.greg.calculator.controler;

import com.greg.calculator.entyty.History;
import com.greg.calculator.logic.ExceptionParserPolishNotation;
import com.greg.calculator.logic.Operator;
import com.greg.calculator.logic.parser.ParserExpression;
import com.greg.calculator.view.desktop.history.MyModelTable;
import com.greg.calculator.view.desktop.history.storage.HistoryStorageService;
import com.greg.calculator.view.desktop.view.CalculatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Created by GREG on 17.07.2014.
 */
@Component(value = "controller")
public class CalculatorController {
    @Autowired
    private CalculatorView calculatorView;
    @Qualifier("historyStorage")
    @Autowired
    private HistoryStorageService historyStorageService;
    @Autowired
    private MyModelTable historyTable;

    @Autowired
    private ParserExpression parserExpression;

    private boolean addOperator = false;
    private boolean addPoint = true;
    private boolean negativNumber = true;
    private int openBracket = 0;
    private int closeBracket = 0;

    public void actionButtons(Character token) {

        String operator = String.valueOf(token);
        String inputText = calculatorView.getInputText();
        String expressionBox = calculatorView.getExpressionText();
        String memory = calculatorView.getMemory();
        if (expressionBox.equals(memory) || expressionBox.length() == 0) {
            if (Character.isDigit(token)) digit(calculatorView, operator, inputText);
            else if (Operator.isBasic(token)) operator(calculatorView, operator, inputText);
            else if (Operator.isOpenBracket(token)) openBracket(calculatorView, operator, inputText);
            else if (Operator.isCloseBracket(token)) closeBracket(calculatorView, operator, inputText);
            else if (token == '.') point(calculatorView, operator, inputText);
        } else {
            calculatorView.setExpressionText(memory);
            if (Operator.isBasic(token)) operator(calculatorView, operator, inputText);
            else calculatorView.setInputText(operator);
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

    public void result() throws IOException {
        String expression = calculatorView.getInputText();
        String memory = calculatorView.getMemory();
        if (expression.length() == 1) return;
        if (!(memory.length() == 0) && expression.equals(memory.substring(6))) return;
        calculatorView.setExpressionText(expression + " = ");
        try {
            String result = parserExpression.parser(expression);
            calculatorView.setInputText(result);
            calculatorView.setMemory("Ans = " + result);
            historyTable.addHistory(new History(new Date(), expression, result));
            historyStorageService.addHistory(new History(new Date(), expression, result));

        } catch (ExceptionParserPolishNotation e1) {
            calculatorView.setExpressionText("Symbol is not supported");
        }
    }

    public void ansResultCalc() {
        if (!(calculatorView.getMemory().length() == 0) && addOperator == false) {
            calculatorView.setInputText(calculatorView.getInputText() + calculatorView.getMemory().substring(6));
            addOperator = true;
            addPoint = true;
            negativNumber = true;
        }
    }

    void reset() {
        calculatorView.setExpressionText(calculatorView.getMemory());
        calculatorView.setInputText("0");
        addOperator = false;
        negativNumber = true;
        openBracket = 0;
    }

    public void backspace() {
        String inputText = calculatorView.getInputText();
        if (inputText.length() == 0) return;
        calculatorView.setInputText(inputText.substring(0, inputText.length() - 1));
        addOperator = true;
        negativNumber = true;
        openBracket++;
    }
}
