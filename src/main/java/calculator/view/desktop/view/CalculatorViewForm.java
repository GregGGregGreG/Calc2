package calculator.view.desktop.view;

import calculator.model.History;
import calculator.view.desktop.ApplicationContext;
import calculator.view.desktop.event.CalculatorEvent;
import calculator.view.desktop.history.MyModelTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.*;
import java.util.List;

import static calculator.view.desktop.view.CalculatorUtil.setToScreenCenter;


public class CalculatorViewForm extends JFrame implements CalculatorView, Serializable {
    public static final Map<Integer, Character> bindButtons = new HashMap<>();

    static {
        bindButtons.put(KeyEvent.VK_0, '0');
        bindButtons.put(KeyEvent.VK_1, '1');
        bindButtons.put(KeyEvent.VK_2, '2');
        bindButtons.put(KeyEvent.VK_3, '3');
        bindButtons.put(KeyEvent.VK_4, '4');
        bindButtons.put(KeyEvent.VK_5, '5');
        bindButtons.put(KeyEvent.VK_6, '6');
        bindButtons.put(KeyEvent.VK_7, '7');
        bindButtons.put(KeyEvent.VK_8, '8');
        bindButtons.put(KeyEvent.VK_9, '9');
        bindButtons.put(KeyEvent.VK_NUMPAD0, '0');
        bindButtons.put(KeyEvent.VK_NUMPAD1, '1');
        bindButtons.put(KeyEvent.VK_NUMPAD2, '2');
        bindButtons.put(KeyEvent.VK_NUMPAD3, '3');
        bindButtons.put(KeyEvent.VK_NUMPAD4, '4');
        bindButtons.put(KeyEvent.VK_NUMPAD5, '5');
        bindButtons.put(KeyEvent.VK_NUMPAD6, '6');
        bindButtons.put(KeyEvent.VK_NUMPAD7, '7');
        bindButtons.put(KeyEvent.VK_NUMPAD8, '8');
        bindButtons.put(KeyEvent.VK_NUMPAD9, '9');
        bindButtons.put(KeyEvent.VK_ADD, '+');
        bindButtons.put(KeyEvent.VK_EQUALS, '+');
        bindButtons.put(KeyEvent.VK_MINUS, '-');
        bindButtons.put(KeyEvent.VK_SUBTRACT, '-');
        bindButtons.put(KeyEvent.VK_MULTIPLY, '×');
        bindButtons.put(KeyEvent.VK_DIVIDE, '÷');
        bindButtons.put(KeyEvent.VK_PERIOD, '.');
    }

    private JPanel mainPanel;

    private JFormattedTextField inputField;
    private JTextField expressionBox;

    private JButton getNumber0;
    private JButton getNumber1;
    private JButton getNumber2;
    private JButton getNumber3;
    private JButton getNumber4;
    private JButton getNumber5;
    private JButton getNumber6;
    private JButton getNumber7;
    private JButton getNumber8;
    private JButton getNumber9;
    private JButton getPointSeparator;

    private JButton getOperatorPlus;
    private JButton getOperatorMinus;
    private JButton getOperatorMultiplication;
    private JButton getOperatorDivision;
    private JButton getOpenBracket;
    private JButton getCloseBracket;

    private JButton getResult;
    private JButton getPreviousResult;
    private JButton resetButton;

    private JButton exponentiation;
    private JButton sqrt;
    private JList list1;

    private JButton viewButton;
    private JTable historyTable;

    private JButton test;
    private JPanel historyPanel;
    private JScrollPane scrol;

    private boolean historyCalculation = false;


    private StringBuilder memoryCalc = new StringBuilder();


    private ArrayList<History> myData = new ArrayList<>();
    private MyModelTable model = new MyModelTable(myData);
    private CalculatorEvent calEvent = (CalculatorEvent) ApplicationContext.getBean("calculatorEvent");


    public CalculatorViewForm() {
        setContentPane(mainPanel);
        setTitle("Калькулятор");
        setVisible(true);
        setSize(276, 296);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        expressionBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        inputField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setToScreenCenter(this);
        calcViewFormComponent();
        bindingButtonsKeyboard();

    }


    private void calcViewFormComponent() {
        List<JButton> viewFormButtons = Arrays.asList(getNumber0, getNumber1, getNumber2, getNumber3, getNumber4,
                getNumber5, getNumber6, getNumber7, getNumber8, getNumber9, getOperatorPlus, getOperatorMinus,
                getOperatorDivision, getOperatorMultiplication, getPointSeparator, getOpenBracket, getCloseBracket
                , exponentiation, sqrt);
        for (JButton setNumber : viewFormButtons) {
            setNumber.addActionListener(calEvent.eventsCalcViewFormButtons());
        }

        resetButton.addActionListener(calEvent.resetCalc());
        getResult.addActionListener(calEvent.getResultCalc());
        getPreviousResult.addActionListener(calEvent.getAnsResultCalc());
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!historyCalculation) {
                    setSize(776, 296);
                    historyCalculation = true;
                } else {
                    setSize(276, 296);
                    historyCalculation = false;
                }
            }
        });
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tets = myData.get(myData.size() - 1).getCurrentHistory();
                System.out.println(tets);
            }
        });
        historyTable.setModel(model);
        DefaultTableCellRenderer DTCRendererColum1 = new DefaultTableCellRenderer();
        DTCRendererColum1.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer DTCRendererColum0 = new DefaultTableCellRenderer();
        DTCRendererColum0.setHorizontalAlignment(JLabel.RIGHT);
        historyTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        historyTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        historyTable.getColumnModel().getColumn(1).setCellRenderer(DTCRendererColum1);
        historyTable.getColumnModel().getColumn(0).setCellRenderer(DTCRendererColum0);


    }

    private void bindingButtonsKeyboard() {
        inputField.addKeyListener(calEvent.keyBlock());
        for (Map.Entry<Integer, Character> entry : bindButtons.entrySet()) {
            Integer entryKey = entry.getKey();
            Character operator = entry.getValue();
            String actionName = "action" + operator;
            AbstractAction action = calEvent.eventsBindingButtonsKeyboard(operator);
            focusingPanel(entryKey, 0, actionName, action);
        }
        focusingPanel(KeyEvent.VK_ENTER, 0, "result", (AbstractAction) calEvent.getResultCalc());
        focusingPanel(KeyEvent.VK_BACK_SPACE, 0, "backspace", (AbstractAction) calEvent.backspaceInputField());
        focusingPanel(KeyEvent.VK_9, InputEvent.SHIFT_MASK, "openBracket", calEvent.eventsBindingButtonsKeyboard('('));
        focusingPanel(KeyEvent.VK_0, InputEvent.SHIFT_MASK, "closeBracket", calEvent.eventsBindingButtonsKeyboard(')'));
        focusingPanel(KeyEvent.VK_6, InputEvent.SHIFT_MASK, "exponentiation", calEvent.eventsBindingButtonsKeyboard('^'));


    }

    private void focusingPanel(Integer entryKey, Integer componentEvent, String actionName, AbstractAction action) {
        InputMap inputFieldMap = inputField.getInputMap(JComponent.WHEN_FOCUSED);
        InputMap mainInputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPanel.getActionMap();
        inputFieldMap.put(KeyStroke.getKeyStroke(entryKey, componentEvent), actionName);
        mainInputMap.put(KeyStroke.getKeyStroke(entryKey, componentEvent), actionName);
        actionMap.put(actionName, action);
    }


    @Override
    public String getInputText() {
        return inputField.getText();
    }

    @Override
    public void setInputText(String text) {
        inputField.setText(text);
        inputField.grabFocus();
    }

    @Override
    public String getExpressionText() {
        return expressionBox.getText();
    }

    @Override
    public void setExpressionText(String text) {
        expressionBox.setText(text);
    }

    @Override
    public String getMemory() {
        return memoryCalc.toString();
    }

    @Override
    public void setMemory(String text) {
        memoryCalc = new StringBuilder();
        memoryCalc.append(text);
    }

    @Override
    public JTable getHistoryTable() {
        return historyTable;
    }

    @Override
    public void addDataHistoryTable(History history) {
        model.setValueAdd(history);
    }
    @Override
    public ArrayList<History> getMyData() {
        return myData;
    }
}