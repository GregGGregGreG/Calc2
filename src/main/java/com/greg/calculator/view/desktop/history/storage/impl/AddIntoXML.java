package com.greg.calculator.view.desktop.history.storage.impl;

import com.greg.calculator.entyty.History;
import com.greg.calculator.entyty.xml.Expression;
import com.greg.calculator.entyty.xml.ExpressionsRegister;
import com.greg.calculator.view.desktop.history.storage.HistoryStorageService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddIntoXML implements HistoryStorageService {
    private final String folder = "file.xml";

    @Override
    public void addHistory(History history) throws IOException {
        File file = new File(folder);
        ArrayList<Expression> expressionsList = new ArrayList<>();
        int expressionID = 0;

        if (!file.exists()) {
            file.createNewFile();
        } else {
            expressionID = getLastIDExpression(file);
            expressionsList = unMarshallerXML(file);
        }

        ExpressionsRegister expressionsRegister = new ExpressionsRegister();
        Expression newExpression = new Expression();

        newExpression.setId(expressionID);
        newExpression.setExpression(history.getExpression());
        newExpression.setResult(history.getResult());
        newExpression.setDate(history.getCreated());

        expressionsList.add(newExpression);
        expressionsRegister.setExpressions(expressionsList);
        marshallerXML(expressionsRegister, file);

    }

    @Override
    public List<History> findAll() {
        return null;
    }

    private ArrayList<Expression> unMarshallerXML(File file) {
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
