package services;

import entity.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(String id, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Planet planet = new Planet();
            planet.setId(id);
            planet.setName(name);
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public Planet getById(String id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Planet.class, id);
        } finally {
            session.close();
        }
    }

    public void update(String id, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Planet planet = session.get(Planet.class, id);
            session.remove(planet);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public List<Planet> getAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Planet", Planet.class).list();
        } finally {
            session.close();
        }
    }
}