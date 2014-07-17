package com.greg.calculator.persistence.service.common;

import com.greg.calculator.entyty.History;
import com.greg.calculator.persistence.dao.common.IHistoryDao;
import com.greg.calculator.persistence.dao.common.IOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

/**
 * Created by GREG on 11.07.2014.
 */
@Service
public class HistoryService extends AbstractService<History> implements IHistoryService {


    @Autowired
    private IHistoryDao dao;

    public HistoryService() {
        super();

    }

    @Override
    protected IOperations<History> getDao() {
        return dao;
    }

}
