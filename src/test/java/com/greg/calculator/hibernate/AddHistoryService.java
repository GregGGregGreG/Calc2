package com.greg.calculator.hibernate;

import com.greg.calculator.config.BaseTest;
import com.greg.calculator.entyty.History;
import com.greg.calculator.view.desktop.history.storage.HistoryStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)

public class AddHistoryService extends BaseTest {

    @Qualifier("historyStorage")
    @Autowired
    private HistoryStorageService addHistoryService;

    @Test
    public final void AddHistory() throws IOException {
        addHistoryService.addHistory(new History(new Date(), "2+2", "22"));
    }
}
