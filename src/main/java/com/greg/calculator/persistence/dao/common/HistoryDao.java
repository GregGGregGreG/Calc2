package com.greg.calculator.persistence.dao.common;

import com.greg.calculator.entyty.History;
import org.springframework.stereotype.Repository;

/**
 * Created by GREG on 11.07.2014.
 */
@Repository
public class HistoryDao extends AbstractHibernateDao<History> implements IHistoryDao {

    public HistoryDao() {
        super();

        setClazz(History.class);
    }

    //API
}
