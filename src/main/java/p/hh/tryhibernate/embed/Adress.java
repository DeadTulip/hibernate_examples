package p.hh.tryhibernate.embed;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Adress {

    private String city;

    private String street;

    private int number;
}
