package p.hh.tryhibernate.inheritance.tableperhierarchy;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Worker")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "woker_type", discriminatorType = DiscriminatorType.STRING, length = 1)
public class AbstrachtWorker {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private int id;

    private String name;
}
