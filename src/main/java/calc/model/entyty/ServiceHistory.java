package calc.model.entyty;

import calc.model.entyty.EntityHistory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class ServiceHistory {

    private EntityManager em;

    public ServiceHistory(EntityManager em) {
        this.em = em;
    }

    public EntityHistory createExpression(String expression, String result, Date data) {
        EntityHistory history = new EntityHistory(expression, result, data);
        em.persist(history);
        return history;
    }

    public List<EntityHistory> findAllHistory() {
        TypedQuery<EntityHistory> query = em.createQuery("SELECT h FROM History h", EntityHistory.class);
        return query.getResultList();
    }

}
