package com.greg.calculator.entyty;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private String result;

    @Column(name = "created")
    private Date created;


    public History() {
        super();
    }

    public History(Date created, String expression, String result) {
        this.expression = expression;
        this.result = result;
        this.created = created;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCreated() {
        return getSimpleDateFormat().format(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History that = (History) o;

        if (id != that.id) return false;
        if (!created.equals(that.created)) return false;
        if (!expression.equals(that.expression)) return false;
        if (!result.equals(that.result)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = (int) (id ^ (id >>> 32));
        result1 = 31 * result1 + expression.hashCode();
        result1 = 31 * result1 + result.hashCode();
        result1 = 31 * result1 + created.hashCode();
        return result1;
    }

    @Override
    public String toString() {
        return expression + '=' + result + '|' + getSimpleDateFormat().format(created);
    }
}
