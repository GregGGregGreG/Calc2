package calculator.view.desktop.history;

import calculator.model.xml.Expression;
import calculator.model.xml.ExpressionsRegister;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AddIntoXML {
    private final String folder = "C:\\folder\\file.xml";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    private String data = dateFormat.format(new Date());


    public void addExpression(String expression, String result) throws JAXBException, IOException {
        File file = new File(folder);
        ArrayList<Expression> expressionsList = new ArrayList<>();
        int expressionID = 0;

        if (!file.exists()) {
            file.createNewFile();
        } else {
            expressionID = getLastIDExpression(file);
            expressionsList = marshallerXML(file);
        }

        ExpressionsRegister expressionsRegister = new ExpressionsRegister();
        Expression newExpression = new Expression();

        newExpression.setId(expressionID);
        newExpression.setExpression(expression);
        newExpression.setResult(result);
        newExpression.setDate(data);

        expressionsList.add(newExpression);
        expressionsRegister.setExpressions(expressionsList);
        marshallerXML(expressionsRegister, file);
        showAllExpression(marshallerXML(file));

    }

    private ArrayList<Expression> marshallerXML(File file) {

        ArrayList<Expression> expressionList = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExpressionsRegister.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ExpressionsRegister expressionsRegister = (ExpressionsRegister) unmarshaller.unmarshal(file);
            expressionList = expressionsRegister.getExpressions();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return expressionList;
    }


    private void marshallerXML(ExpressionsRegister expressionsRegister, File file) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExpressionsRegister.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(expressionsRegister, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private int getLastIDExpression(File file) {
        ArrayList<Expression> expressionsList;
        int lastID = 0;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExpressionsRegister.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ExpressionsRegister expressionsRegister = (ExpressionsRegister) unmarshaller.unmarshal(file);
            expressionsList = expressionsRegister.getExpressions();
            Expression expression = expressionsList.get(expressionsList.size() - 1);
            lastID = expression.getId();
            lastID++;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return lastID;
    }

    private void showAllExpression(ArrayList<Expression> expressionList) {
        String formatHead = "%1$-5s %2$-30s %3$-20s %4$-18s \n";
        String formatRows = "|%1$-5s|%2$-30s|%3$-20s|%4$-18s|\n";

        System.out.println(System.lineSeparator());
        System.out.format(formatHead, "ID", "Expression", "Result", "Date");
        for (Expression costumer : expressionList) {
            System.out.format(formatRows,
                    costumer.getId(),
                    costumer.getExpression(),
                    costumer.getResult(),
                    costumer.getDate());
        }
    }
}
