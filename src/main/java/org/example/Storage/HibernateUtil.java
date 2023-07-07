package org.example.Storage;

import lombok.Getter;
import org.example.tests.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class HibernateUtil { // клас для зьеднання з бд
    // налаштовуємо все в конструкторі обгортаємо в сінгелтон
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static { // ініціалізуємо в статиці щоб зайвий раз не викликати сінгелтон
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() { // приватний конструктор створює обьект і там ми вже конфігуруємо його
        sessionFactory = new Configuration()
                .addAnnotatedClass(Person.class) // додаємо клас з яким працюємо
                .buildSessionFactory();
    }
    // тут при зверненні визиває обьект і конфігурує все
    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

// само не закриється треба очищати ресурси

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {

       // new DatabaseInitService().initDb();

        HibernateUtil util = HibernateUtil.getInstance();

        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Person p = new Person();
        // буде ніби два звернення до бази, гібернейт одразу за раз не вміє
                p.setAddressList(Arrays.asList("address1", "address2")); //  добавляєм дві адреси
                session.persist(p);
            transaction.commit();
        session.close();

        /* Session session = util.getSessionFactory().openSession();
        List<Person> persons = session.createQuery("from Person", Person.class).list();
        System.out.println("persons = " + persons);
        session.close(); */
    }
}
