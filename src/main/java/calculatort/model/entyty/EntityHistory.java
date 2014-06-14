package calculatort.model.entyty;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "history")
public class EntityHistory {

    @Id
    @Column(name = "id_expression")
    private int id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private String result;

    @Column(name = "date")
    private Date date;

    public EntityHistory() {
    }

    public EntityHistory(String expression, String result, Date date) {
        this.expression = expression;
        this.result = result;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expression " + expression + ' ' + result + ' ' + date + '}';
    }
}
