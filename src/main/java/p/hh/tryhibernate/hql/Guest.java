package p.hh.tryhibernate.hql;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Guest {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private int id;

    private String name;

    private boolean male;

    private int age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest")
    private List<BookingOrder> orderList = new ArrayList<>();

}
