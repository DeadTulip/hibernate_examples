package p.hh.tryhibernate.hql;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class BookingOrder {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Order(id=").append(id)
                .append(", guest_id=").append(guest.getId())
                .append(", product_id=").append(product.getId()).append(")");
        return sb.toString();
    }
}
