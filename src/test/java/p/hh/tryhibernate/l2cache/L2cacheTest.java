package p.hh.tryhibernate.l2cache;

import net.sf.ehcache.CacheManager;
import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;


public class L2cacheTest extends AbstractHibernateLabTest {
    @Override
    protected String getHibernateConfigFileName() {
        return "l2cache_hibernate.cfg.xml";
    }

    @Test
    public void test() {
        HeavyObject ho = new HeavyObject();
        ho.setName("Elephant");
        ho.setDescription("A big animal.");

        doTransaction(session -> {
            session.save(ho);
        });

        doTransaction(session -> {
            System.out.println("Get heavy object");
            session.get(HeavyObject.class, 1);
        });

        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("p.hh.tryhibernate.l2cache.HeavyObject").getSize();
        System.out.println("Cache size = " + size);

        // the cache should save HeavyObject with id 1
        // the following get operation won't go to database

        doTransaction(session -> {
            System.out.println("Get heavy object");
            session.get(HeavyObject.class, 1);
        });

        System.out.println("Print table");
        printTable(HeavyObject.class);
    }
}
