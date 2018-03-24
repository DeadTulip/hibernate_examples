package p.hh.tryhibernate.hql;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    private String name;

    private int price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<BookingOrder> orderList = new ArrayList<>();
}
