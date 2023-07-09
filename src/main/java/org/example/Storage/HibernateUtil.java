package org.example.Storage;

import lombok.Getter;
import org.example.tests.Person;
import org.example.tests.PersonInfo;
import org.example.tests.Project;
import org.example.tests.WorkPlace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Collections;
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
                .addAnnotatedClass(PersonInfo.class)
                .addAnnotatedClass(WorkPlace.class)
                .addAnnotatedClass(Project.class)
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

        // додавання адреси в Person

          /* Session session = util.getSessionFactory().openSession();
             Transaction transaction = session.beginTransaction();
               Person p = new Person();
        // буде ніби два звернення до бази, гібернейт одразу за раз не вміє
            p.setAddressList(Arrays.asList("addressE", "addressO")); //  добавляєм дві адреси
            session.persist(p);
         transaction.commit();
         session.close(); */

        // вивід всіх Person

       /*  Session session = util.getSessionFactory().openSession();
        List<Person> persons = session.createQuery("from Person", Person.class).list();
        System.out.println("persons = " + persons);
        session.close(); */

        //  вивід якоїсь конкретної Person

        /* Session session = util.getSessionFactory().openSession();
        WorkPlace workPlace = session.get(WorkPlace.class,1); // достань данні з класу WorkPlace з id 1
        System.out.println("Work place = " + workPlace); // виведе місця роботи
        System.out.println(" Person Info = " + workPlace.getPerson()); // виведе інфу про людину
        session.close(); */

        // додавання инфо до таблиці конкретного Person

       /* Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Person p = session.get(Person.class, 3L);

        PersonInfo info = new PersonInfo();
        info.setPersonId(p.getId());
        info.setName("Some name Three"); // вносимо данні в таблицю person_info

        session.persist(info); // add to session
      //  p.setPersonInfo(info);

        session.persist(p);
        transaction.commit();
        session.close(); */

        // додавання місця роботи до таблиці конкретного Person

       /* Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Person p = session.get(Person.class, 3L); // достали персон з id = 2

        WorkPlace workPlace = new WorkPlace();
        workPlace.setPlace("ANB");

        workPlace.setPerson(p); // додали данні конкретної персони у нашому випадку з id = 2


        p.setWorkPlaces(Arrays.asList(workPlace)); // додаємо список місць роботи

        session.persist(workPlace); // зберігаємо явним чином
        session.persist(p); // add to session
        transaction.commit();
        session.close(); */

        // додавання кількості проєктів над якими працює конкретний  Person

       /* Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Project project = new Project();
        project.setName("Foton Project");
        session.persist(project);
        // далі отримуємо якусь конкретну персону і додаємо йому проджектів
        Person person = session.get(Person.class, 3L); // проєкт додали першій персоні
        person.setProjects(Collections.singleton(project));

        session.persist(person);
        transaction.commit();
        session.close(); */

       /* Person person = getPersonById(2);
        System.out.println("person id = " + person.getId());
        System.out.println("Person projects = " + person.getProjects()); */

        Person person = getPersonById(1, PersonInItFild.project, PersonInItFild.workplase);
        System.out.println("person id = " + person.getId());
        System.out.println("Person projects = " + person.getProjects()); // + проєкти
        System.out.println("Persons worketplases" + person.getWorkPlaces()); // + місця роботи

    }

   /* private static Person getPersonById(long id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Person person = session.get(Person.class, id);
        for (Project project : person.getProjects()) { // поки не закрили сесію можемо отримати данні із звязних таблиць

        }
        session.close();

        return person;
    } */

    private static Person getPersonById(long id, PersonInItFild... inItFilds) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Person person = session.get(Person.class, id);

        for (PersonInItFild initFild : inItFilds) {
            switch (initFild) {
                case project:
                    for (Project project : person.getProjects()) ;
                    break;
                case workplase:
                    for (WorkPlace workPlace : person.getWorkPlaces()) ;
                    break;
            }
        }


        session.close();

        return person;
    }


    public enum PersonInItFild {
        project,
        workplase
    }
}


// SELECT * FROM person