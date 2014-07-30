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


@Component(value = "controller")
public class CalculatorController {
    @Autowired
    private CalculatorView view;
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

        String inputText = view.getInputText();
        String expressionBox = view.getExpressionText();
        String memory = view.getMemory();

        if (expressionBox.equals(memory) || expressionBox.length() == 0) {
            if (Character.isDigit(token)) digit(token, inputText);
            else if (Operator.isBasic(token)) operator(token, inputText);
            else if (Operator.isOpenBracket(token)) openBracket(token, inputText);
            else if (Operator.isCloseBracket(token)) closeBracket(token, inputText);
            else if (token == '.') point(token, inputText);
        } else {
            view.setExpressionText(memory);
            if (Operator.isBasic(token)) operator(token, inputText);
            else view.setInputText(String.valueOf(token));
        }
    }

    private void point(Character symbol, String inputText) {
        if (addPoint) {
            view.setInputText(view.getInputText() + symbol);
            addPoint = false;
        }
    }

    private void openBracket(Character symbol, String inputText) {
        if (inputText.equals("0") || inputText.equals("")) {
            view.setInputText(String.valueOf(symbol));
            openBracket++;
        } else if (addOperator == false) {
            view.setInputText(inputText + " " + symbol);
            openBracket++;
        }
    }

    private void closeBracket(Character symbol, String inputText) {
        if (inputText.equals("0")) return;
        else if (!(closeBracket == openBracket) && addOperator == true) {
            view.setInputText(inputText + symbol + " ");
            openBracket--;
        }
    }

    private void operator(Character symbol, String inputText) {
        if (addOperator) {
            view.setInputText(inputText + " " + symbol + " ");
            addOperator = false;
            addPoint = true;
        } else if (Operator.MINUS.equals(symbol) && negativNumber) {
            if (inputText.equals("0")) {
                view.setInputText(String.valueOf(symbol));
            } else {
                view.setInputText(inputText + symbol);
            }
            negativNumber = false;
        } else if (Operator.SQRT.equals(symbol)) {
            if (inputText.equals("0")) {
                view.setInputText(String.valueOf(symbol));
            } else {
                view.setInputText(inputText + symbol);
            }
        }
    }

    private void digit(Character symbol, String inputText) {
        if (inputText.equals("0")) view.setInputText(String.valueOf(symbol));
        else view.setInputText(inputText + symbol);
        addOperator = true;
        addPoint = true;
        negativNumber = true;
    }

    public void result() throws IOException {
        String expression = view.getInputText();
        String memory = view.getMemory();
        if (expression.length() == 1) return;
        if (memory.length() > 0 && expression.equals(memory.substring(6))) return;
        view.setExpressionText(expression + " = ");
        try {
            String result = parserExpression.parser(expression);
            view.setInputText(result);
            view.setMemory("Ans = " + result);
            historyTable.addHistory(new History(new Date(), expression, result));
            historyStorageService.addHistory(new History(new Date(), expression, result));

        } catch (ExceptionParserPolishNotation e1) {
            view.setExpressionText("Symbol is not supported");
        }
    }

    public void ansResultCalc() {
        if (view.getMemory().length() > 0 && addOperator == false) {
            view.setInputText(view.getInputText() + view.getMemory().substring(6));
            addOperator = true;
            addPoint = true;
            negativNumber = true;
        }
    }

    void reset() {
        view.setExpressionText(view.getMemory());
        view.setInputText("0");
        addOperator = false;
        negativNumber = true;
        openBracket = 0;
    }

    public void backspace() {
        String inputText = view.getInputText();
        if (inputText.length() == 0) return;
        view.setInputText(inputText.substring(0, inputText.length() - 1));
        addOperator = true;
        negativNumber = true;
        openBracket++;
    }
}
