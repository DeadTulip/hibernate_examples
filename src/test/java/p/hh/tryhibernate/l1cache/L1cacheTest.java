package p.hh.tryhibernate.l1cache;


import org.junit.Test;
import p.hh.tryhibernate.basic.BasicPerson;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;

public class L1cacheTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "basic_hibernate.cfg.xml";
    }

    @Test
    public void testL1cacheWithGet() {
        final BasicPerson person = new BasicPerson();
        person.setName("haihan");
        person.setAge(30);
        person.setMale(true);

        doTransaction(session -> {
            session.save(person);
            System.out.println("Fetch in the same session");
            session.get(BasicPerson.class, 1); // it does not go to database
        });

        doTransaction(session -> {
            System.out.println("Fetch in a different session");
            session.get(BasicPerson.class, 1);
        });
    }

    @Test
    public void testL1cacheWithDelete() {
        final BasicPerson person = new BasicPerson();
        person.setName("haihan");
        person.setAge(30);
        person.setMale(true);

        doTransaction(session -> {
            System.out.println("Save entity 1");
            session.persist(person);


            System.out.println("Delete in sesson");
            session.delete(person);
            // it does not go to database, because it is overwrite by the following save

            System.out.println("Save entity again");
            session.persist(person);
        });

        printTable(BasicPerson.class);

    }
}
