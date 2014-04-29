package calc.view.desktop;

/**
 * Created by GREG on 28.04.2014.
 */
public interface CalculatorView {
    void setInputText(String text);

    String getInputText();

    void setExpressionText(String text);

    String getExpressionText();

    void setMemory(String text);

    String getMemory();
}