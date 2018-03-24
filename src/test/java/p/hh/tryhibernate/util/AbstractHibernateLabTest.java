package p.hh.tryhibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractHibernateLabTest {

    protected abstract String getHibernateConfigFileName();

    @After
    public void tearDown() {
        closeAllSessions();
    }

    private SessionFactory sessionFactory;
    private List<Session> openedSessions = new ArrayList<>();

    protected void doTransaction(LabService service) {
        Session session = createSessionFactory().openSession();
        openedSessions.add(session);
        session.beginTransaction();
        service.accept(session);
        session.getTransaction().commit();
    }

    protected void closeAllSessions() {
        openedSessions.forEach(Session::close);
    }

    protected void printTable(String tableName) {
        doTransaction((session) -> {
            List result = session.createQuery("from " + tableName).list();
            System.out.println("---------- " +  tableName + " ----------");
            result.stream().forEach(System.out::println);
            System.out.println("------------------------------");
        });
    }

    protected void printTable(Class tableClass) {
        printTable(tableClass.getSimpleName());
    }

    private File getHibernateConfigFile() {
        return new File("src/test/resources/" + getHibernateConfigFileName());
    }
    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure(getHibernateConfigFile()).buildSessionFactory();
        }
        return sessionFactory;
    }
}
