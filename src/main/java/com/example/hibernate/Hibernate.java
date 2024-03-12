package com.example.hibernate;


import com.example.hibernate.entity.Book;
import com.example.hibernate.entity.SubClass;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.TRUE;

import static org.hibernate.cfg.AvailableSettings.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Map;
import java.util.function.Consumer;

import static jakarta.persistence.Persistence.createEntityManagerFactory;
import static org.hibernate.cfg.AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION;
import static org.hibernate.tool.schema.Action.CREATE;

public class Hibernate implements SubClass{
    protected int sample;

    //Sesstion Factory
    /*
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger("");
        var sessionFactory = new Configuration()
                .addPackage("com.example.hibernate.entity")
                .addAnnotatedClass(Book.class)
                // use H2 in-memory database
                .setProperty(JAKARTA_JDBC_URL, "jdbc:h2:mem:db1")
                .setProperty(JAKARTA_JDBC_USER, "sa")
                .setProperty(JAKARTA_JDBC_PASSWORD, "")
                // use Agroal connection pool
                .setProperty("hibernate.agroal.maxSize", "20")
                // display SQL in console
                .setProperty(SHOW_SQL, TRUE.toString())
                .setProperty(FORMAT_SQL, TRUE.toString())
                .setProperty(HIGHLIGHT_SQL, TRUE.toString())
                .buildSessionFactory();
        sessionFactory.getSchemaManager().exportMappedObjects(true);
        logger.info(sessionFactory.getClass().toString());
        // persist an entity
        sessionFactory.inTransaction(session -> {
            session.persist(new Book("9781932394153", "Hibernate in Action"));
        });

        // query data using HQL
        sessionFactory.inSession(session -> {
            logger.info(session.createSelectionQuery("select isbn||': '||title from Book", String.class).getSingleResult());
        });
    }*/


    //EntityManager
    public static void main(String[] args) {
        SubClass a = new Hibernate();


        Logger logger = LoggerFactory.getLogger("");
        var factory = createEntityManagerFactory("example",
                // export the inferred database schema
                Map.of(JAKARTA_HBM2DDL_DATABASE_ACTION, CREATE));
        logger.info(factory.createEntityManager().getClass().toString());

        // persist an entity
        inSession(factory, entityManager -> {
            entityManager.persist(new Book("9781932394153", "Hibernate in Action"));
        });

        // query data using HQL
        inSession(factory, entityManager -> {
            logger.info((String) entityManager.createQuery("select isbn||': '||title from Book").getSingleResult());
        });

        // query data using criteria API
        inSession(factory, entityManager -> {
            var builder = factory.getCriteriaBuilder();
            var query = builder.createQuery(String.class);
            var book = query.from(Book.class);
//            query.select(builder.concat(builder.concat(book.get(Book_.isbn), builder.literal(": ")),
//                    book.get(Book_.title)));
            logger.info(entityManager.createQuery(query).getSingleResult());
        });
    }

    static void inSession(EntityManagerFactory factory, Consumer<EntityManager> work) {
        var entityManager = factory.createEntityManager();
        var transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

}
