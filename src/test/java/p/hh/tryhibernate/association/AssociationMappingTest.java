package p.hh.tryhibernate.association;


import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AssociationMappingTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "association_hibernate.cfg.xml";
    }

    @Test
    public void testAssociationMapping() {
        Car car = new Car();
        List<Wheel> wheels = IntStream.range(1, 5)
                .mapToObj(i -> makeWheel(car, "wheel" + i))
                .collect(Collectors.toList());
        Engine engine = new Engine();
        engine.setOwningCar(car);
        engine.setDescription("Engine1");

        Driver driver1 = new Driver();
        Driver driver2 = new Driver();

        doTransaction(session -> {

            session.save(car);
            for (Wheel w : wheels) {
                session.save(w);
            }
            session.save(engine);
            session.save(driver1);
            session.save(driver2);
        });

        doTransaction(session -> {
            Car savedCar = session.get(Car.class, car.getId());
            Driver savedDriver1 = session.get(Driver.class, driver1.getId());
            Driver savedDriver2 = session.get(Driver.class, driver2.getId());

            savedCar.getDrivers().add(savedDriver1);
            savedCar.getDrivers().add(savedDriver2);

            session.save(car);
        });

        printTable(Car.class);
        printTable(Wheel.class);
        printTable(Engine.class);
        printTable(Driver.class);
    }

    private Wheel makeWheel(Car owningCar, String description) {
        Wheel w = new Wheel();
        w.setOwningCar(owningCar);
        w.setDescription(description);
        return w;
    }
}
