package p.hh.tryhibernate.basic;


import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;

public class BasicMappingTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "basic_hibernate.cfg.xml";
    }

    @Test
    public void testMapping() {

        final BasicPerson person = new BasicPerson();
        person.setName("haihan");
        person.setAge(30);
        person.setMale(true);

        doTransaction((session) -> {
            session.save(person);
        });

        printTable(BasicPerson.class);
    }

}
