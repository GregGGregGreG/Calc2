package calc.view.desktop;

/**
 * Created by GREG on 28.04.2014.
 */
public interface CalculatorView {
    String getInputText();

    void setInputText(String text);

    String getExpressionText();

    void setExpressionText(String text);

    String getMemory();

    void setMemory(String text);

    void addMemory(String text);




}
