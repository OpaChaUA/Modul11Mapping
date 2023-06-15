package services;

import entity.Client;
import entity.Planet;
import entity.Ticket;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudService {
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(Client client, Planet fromPlanet, Planet toPlanet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Ticket ticket = new Ticket();
                ticket.setClient(client);
                ticket.setFromPlanet(fromPlanet);
                ticket.setToPlanet(toPlanet);
                session.persist(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } catch (PropertyValueException e) {
            System.out.println("\nArguments of method createTicket() can't be null");
        }
    }

    public Ticket getById(long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Ticket.class, id);
        } finally {
            session.close();
        }
    }

    public void update(Client client, long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Ticket ticket = session.get(Ticket.class, id);
            ticket.setClient(client);
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Ticket ticket = session.get(Ticket.class, id);
            session.remove(ticket);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Ticket> getTickets() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Ticket", Ticket.class).list();
        } finally {
            session.close();
        }
    }
}
