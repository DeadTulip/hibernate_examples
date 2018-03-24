package p.hh.tryhibernate.inheritance.tableperhierarchy;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("T")
public class Tester extends AbstrachtWorker {

    private String testingJob;
}
