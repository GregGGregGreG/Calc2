package com.greg.calculator.view.desktop.history.storage.impl;

import com.greg.calculator.entyty.History;
import com.greg.calculator.persistence.service.common.IHistoryService;
import com.greg.calculator.view.desktop.history.storage.HistoryStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by GREG on 11.07.2014.
 */
@Component(value = "addIntoDB")
public class AddIntoDB implements HistoryStorageService {

    @Autowired
    private IHistoryService historyService;

    @PostConstruct
    public void init() {
        System.out.println(this);
    }

    @Override
    public void addHistory(History history) throws IOException {
        historyService.create(history);
    }

    @Override
    public List<History> findAll() {
        return historyService.findAll();
    }
}
