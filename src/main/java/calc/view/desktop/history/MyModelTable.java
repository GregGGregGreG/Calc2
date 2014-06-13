package calc.view.desktop.history;

import calc.model.History;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyModelTable extends AbstractTableModel {
    final String[] colNames = {"Expression", "Result", "Data"};
    private List<History> histories = new ArrayList<>();

    public MyModelTable(List<History> histories) {
        this.histories = histories;
    }

    @Override
    public int getRowCount() {
        return histories.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        History history = histories.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return history.getExpression();
            case 1:
                return history.getResult();
            case 2:
                return history.getCreated();
            default:
                throw new RuntimeException("Only three columns");
        }
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setValueAdd(History newData) {
        histories.add(newData);
        fireTableDataChanged();
    }


}
