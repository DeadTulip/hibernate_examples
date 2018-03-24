package p.hh.tryhibernate.inheritance.tablepersubclass;


import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;

public class SubclassMappingTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "tablepersubclass_hibernate.cfg.xml";
    }

    // switch the inheritance stategy between JOINED and TABLE_PER_CLASS
    // observer the hibernate log
    @Test
    public void testMapping() {
        DiningRoom diningRoom = new DiningRoom();
        diningRoom.setName("DiningRoom1");
        diningRoom.setDishwasher("Siemens dish washer");
        diningRoom.setFridge("Siemens fridge");
        diningRoom.setOven("Philips over");

        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setName("MeetingRoom1");
        meetingRoom.setProjector("Sony projector");
        meetingRoom.setSpeaker("Sumsung speaker");
        meetingRoom.setTable("Ikea table");

        doTransaction(session -> {
            session.save(diningRoom);
            session.save(meetingRoom);
        });

        printTable(DiningRoom.class);
        printTable(MeetingRoom.class);
    }
}
