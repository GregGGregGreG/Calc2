package calculatort.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class History {
    private String expression;
    private String result;
    private Date created;

    public History(Date created, String expression, String result) {
        this.expression = expression;
        this.result = result;
        this.created = created;
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

    public String getCreated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return dateFormat.format(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCurrentHistory() {
        String history = expression + result + "  " + getCreated();
        return history;
    }
}
