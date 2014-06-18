package calculator.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Userr on 17.06.2014.
 */
@XmlRootElement(name = "expression")
public class Expression {

    private String date;
    private String expression;
    private String result;
    private int id;

//    public Expression(String date, String expression, String result, int id) {
//        this.date = date;
//        this.expression = expression;
//        this.result = result;
//        this.id = id;
//    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "expression{" +
                ", expression='" + expression + '\'' +
                ", result='" + result + '\'' +
                "date=" + date +
                '}';
    }
}
