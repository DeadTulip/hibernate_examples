package p.hh.tryhibernate.util;


import org.hibernate.Session;

import java.util.function.Consumer;

public interface LabService extends Consumer<Session> {

}
