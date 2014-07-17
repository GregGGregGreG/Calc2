package com.greg.calculator.view.desktop.history.storage.impl;

import com.greg.calculator.entyty.History;
import com.greg.calculator.view.desktop.history.storage.HistoryStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.List;

/**
 * Created by GREG on 13.07.2014.
 */

public class SaveAll implements HistoryStorageService {

    public SaveAll() {
        System.out.println(this);
    }

    @Qualifier("addIntoDB")
    @Autowired
    private HistoryStorageService addIntoDBd;

    @Qualifier("addIntoFile")
    @Autowired
    private HistoryStorageService addIntoFile;


    @Override
    public void addHistory(History history) throws IOException {
        addIntoDBd.addHistory(history);
       addIntoFile.addHistory(history);
    }

    @Override
    public List<History> findAll() {
        return null;
    }


}
