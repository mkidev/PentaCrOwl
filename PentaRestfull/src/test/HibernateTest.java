package test;

/**
 * Created by marcel on 15.04.2015.
 */
public class HibernateTest {
   /* public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        User person = new User("Marcel");
        session.save(person);

        session.getTransaction().commit();

        Query q = session.createQuery("From Person ");

        List<User> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());
        resultList.forEach(System.out::println);

        HibernateUtil.shutdown();

    }*/
}
