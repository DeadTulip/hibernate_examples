package p.hh.tryhibernate.hql;


import org.hibernate.Session;
import org.junit.Test;
import p.hh.tryhibernate.util.AbstractHibernateLabTest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class HqlTest extends AbstractHibernateLabTest {

    @Override
    protected String getHibernateConfigFileName() {
        return "hql_hibernate.cfg.xml";
    }

    @Test
    public void testHql() {
        doTransaction(session -> {
            List<Guest> guests = createGuests(session, 5);
            List<Product> products = createProducts(session, 5);
            createOrders(session, guests, products, 3);
        });

        printTable(Guest.class);
        printTable(Product.class);
        printTable(BookingOrder.class);

        doTransaction(session -> {
            printResult(session, "from Product p where p.price > 50");
            printResult(session, "from Product p where p.name like '%io%'");
            printResult(session, "from Guest g where size(g.orderList) = 0");
            printResult(session, "select g.id from Guest g join g.orderList orders where orders.product.price > 200");
        });
    }

    private void printResult(Session session, String query) {
        System.out.println("Query ---> " + query);
        session.createQuery(query).list().stream().forEach(System.out::println);
        System.out.println("----------");
    }

    private List<Guest> createGuests(Session session, int amount) {
        String[] names = new String[] {
                "Antionette", "Devora", "Shu", "Bryce", "Jonathan", "Thora", "Josie", "Emelia","Kaylene"
                ,"Damion","Cathrine", "Ferdinand", "Madalene", "Shirlee", "Gabriele", "Domenic", "Ellan"
                , "Melissa", "Yon", "Katheleen"};

        List<Guest> guestList = IntStream.range(0, amount).mapToObj( i -> {
                Guest guest = new Guest();
                int index = ThreadLocalRandom.current().nextInt(0, names.length);
                guest.setName(names[index]);
                int age = ThreadLocalRandom.current().nextInt(20, 60);
                guest.setAge(age);

                session.save(guest);
                return guest;
        }
        ).collect(Collectors.toList());

        return  guestList;
    }

    private List<Product> createProducts(Session session, int amount) {
        String[] names = new String[] {
                "Fix Is", "Sao-Lam", "Lexitough", "Isfan", "Biowarm", "Treetraxdom", "Kan Tam", "Funfax",
                "Zim Nimin", "Kayozeis", "Faxlex", "Vol Touch", "Voltstring", "Biolam", "An-Dom", "Lat Hottam",
                "Whitetop", "Sum Keyfresh", "Roundfresh", "Groove-Plus"
        };

        List<Product> productList = IntStream.range(0, amount).mapToObj( i -> {
                Product product = new Product();
                int index = ThreadLocalRandom.current().nextInt(0, names.length);
                product.setName(names[index]);
                int price = ThreadLocalRandom.current().nextInt(20, 600);
                product.setPrice(price);

                session.save(product);
                return product;
        }
        ).collect(Collectors.toList());

        return  productList;
    }

    private List<BookingOrder> createOrders(Session session, List<Guest> guests, List<Product> products, int amount) {
        List<BookingOrder> orderList = IntStream.range(0, amount).mapToObj( i -> {
            BookingOrder bookingOrder = new BookingOrder();
            int guestIndex = ThreadLocalRandom.current().nextInt(0, guests.size());
            bookingOrder.setGuest(guests.get(guestIndex));
            int productIndex = ThreadLocalRandom.current().nextInt(0, products.size());
            bookingOrder.setProduct(products.get(productIndex));

            session.save(bookingOrder);
            return bookingOrder;
        }).collect(Collectors.toList());
        return orderList;
    }
}
