package p.hh.tryhibernate.inheritance.tablepersubclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractRoom {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "room_id")
    private int id;

    private String name;

}
