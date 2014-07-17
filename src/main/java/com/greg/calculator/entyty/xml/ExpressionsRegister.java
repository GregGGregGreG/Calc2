package com.greg.calculator.entyty.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by Userr on 17.06.2014.
 */
@XmlRootElement(name = "history")
public class ExpressionsRegister {

    private ArrayList<Expression> expressions = new ArrayList<>();

    @XmlElement(name = "expression")
    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }
}
