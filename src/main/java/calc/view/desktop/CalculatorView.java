package calc.view.desktop;

public interface CalculatorView {
    String getInputText();

    void setInputText(String text);

    String getExpressionText();

    void setExpressionText(String text);

    String getMemory();

    void setMemory(String text);

    public void cleanMemory();
}