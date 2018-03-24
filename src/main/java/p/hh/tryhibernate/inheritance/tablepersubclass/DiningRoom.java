package p.hh.tryhibernate.inheritance.tablepersubclass;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
public class DiningRoom extends AbstractRoom {

    private String oven;

    private String fridge;

    private String dishwasher;

}
