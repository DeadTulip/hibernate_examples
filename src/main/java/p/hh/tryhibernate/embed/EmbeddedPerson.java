package p.hh.tryhibernate.embed;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EmbeddedPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    private String name;

    private int age;

    private boolean male;

    @Embedded
    private Adress adress;
}
