package p.hh.tryhibernate.association;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
@Table
public class Driver {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "Car_Driver",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> drivedCars = new ArrayList<>();

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Driver(").append("id=").append(id).append(", drivedCars[");
        String.join(", ", drivedCars.stream()
                .map(car -> Integer.toString(car.getId()))
                .collect(Collectors.toList())
        );
        sb.append("])");
        return sb.toString();
    }
}
