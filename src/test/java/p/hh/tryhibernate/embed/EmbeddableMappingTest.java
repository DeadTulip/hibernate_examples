package p.hh.tryhibernate.embed;

import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;


public class EmbeddableMappingTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "embed_hibernate.cfg.xml";
    }

    @Test
    public void test() {
        final EmbeddedPerson person = new EmbeddedPerson();
        person.setName("haihan");
        person.setAge(30);
        person.setMale(true);

        Adress adress = new Adress();
        adress.setCity("Utrecht");
        adress.setStreet("Musicallaan");
        adress.setNumber(413);

        person.setAdress(adress);

        doTransaction((session) -> {
            session.save(person);
        });

        printTable(EmbeddedPerson.class);
    }
}
