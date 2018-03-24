package p.hh.tryhibernate.association;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Car {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @OneToOne(mappedBy = "owningCar")
    private Engine engine;

    @OneToMany(mappedBy = "owningCar")
    private List<Wheel> wheels;

    @ManyToMany
    @JoinTable(
            name = "Car_Driver",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "dirver_id")
    )
    private List<Driver> drivers = new ArrayList<>();


}
