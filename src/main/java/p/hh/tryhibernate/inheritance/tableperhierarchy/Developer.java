package p.hh.tryhibernate.inheritance.tableperhierarchy;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("D")
public class Developer extends AbstrachtWorker {

    private String developmentJob;
}
