package p.hh.tryhibernate.association;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table
public class Wheel {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wheel_id")
    private int id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car owningCar;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Wheel(").append("id=").append(id).append(", ")
                .append("description=").append(description).append(", ")
                .append("owningCar=Car(id=").append(owningCar.getId()).append("))");
        return sb.toString();
    }
}
