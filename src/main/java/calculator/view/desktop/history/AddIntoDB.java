package calculator.view.desktop.history;

import calculator.model.entyty.EntityHistory;
import calculator.model.entyty.ServiceHistory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;


public class AddIntoDB {
    public AddIntoDB() {
        em.getTransaction().begin();
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AddIntoDB");
    private EntityManager em = emf.createEntityManager();
    private ServiceHistory serviceHistory = new ServiceHistory(em);


    public void addData(String expression, String result, Date date) {

        EntityHistory entityHistory = serviceHistory.createExpression(expression, result, date);
        em.getTransaction().commit();
    }
}
