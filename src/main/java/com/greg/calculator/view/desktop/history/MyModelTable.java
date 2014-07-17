package com.greg.calculator.view.desktop.history;

import com.greg.calculator.entyty.History;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyModelTable extends AbstractTableModel {
    private final String[] nameColumns = {"Expression", "Result", "Data"};
    private List<History> historyList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return historyList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        History historyObj = this.historyList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return historyObj.getExpression();
            case 1:
                return historyObj.getResult();
            case 2:
                return historyObj.getCreated();
            default:
                throw new RuntimeException("Only three columns");
        }
    }

    @Override
    public String getColumnName(int column) {
        return nameColumns[column];
    }

    public void addHistory(History expression) {
        historyList.add(expression);
        fireTableDataChanged();
    }
}
