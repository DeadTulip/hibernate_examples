package p.hh.tryhibernate.inheritance.tablepersubclass;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
public class MeetingRoom extends AbstractRoom {

    private String table;

    private String projector;

    private String speaker;
}
