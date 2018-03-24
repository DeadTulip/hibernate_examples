package p.hh.tryhibernate.inheritance.tableperhierarchy;


import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;

public class HierarchyMappingTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "tableperhierarchy_hibernate.cfg.xml";
    }

    @Test
    public void test() {
        Developer developer = new Developer();
        developer.setName("Dianna");
        developer.setDevelopmentJob("print Hello world");

        Tester tester = new Tester();
        tester.setName("Tom");
        tester.setTestingJob("perform system test");

        doTransaction(session -> {
            session.save(developer);
            session.save(tester);
        });

        printTable(AbstrachtWorker.class);
    }
}
