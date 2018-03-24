package p.hh.tryhibernate.basic;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BasicPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    private String name;

    private int age;

    private boolean male;

}
