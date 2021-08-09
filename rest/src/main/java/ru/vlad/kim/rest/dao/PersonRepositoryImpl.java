package ru.vlad.kim.rest.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public void savePerson(String person) {
        String sql = "INSERT INTO person (information) VALUES (:information)";

        Session session = new Configuration().configure()
                .buildSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createSQLQuery(sql)
                .setParameter("information", person);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
