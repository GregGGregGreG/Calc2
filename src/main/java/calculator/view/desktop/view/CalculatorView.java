package calculator.view.desktop.view;

import calculator.model.History;

import javax.swing.*;
import java.util.ArrayList;

public interface CalculatorView {
    String getInputText();

    void setInputText(String text);

    String getExpressionText();

    void setExpressionText(String text);

    String getMemory();

    void setMemory(String text);

    JTable getHistoryTable();

    void addDataHistoryTable(History history);

    ArrayList<History> getMyData();

//    void addDataHistoryTable(JTable historyTable);
}