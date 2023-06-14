package Servais;

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

    public void createTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);
            session.persist(ticket);
            transaction.commit();
        } catch (PropertyValueException e) {
            System.out.println("\nArguments of method createTicket() can't be null");
        }
    }

    public Ticket getTicketById(long id) {
        Session session = sessionFactory.openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }

    public void updateTicket(Client client, long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        ticket.setClient(client);
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

    public void deleteTicket(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        session.remove(ticket);
        transaction.commit();
        session.close();
    }

    public List<Ticket> getAllTickets() {
        Session session = sessionFactory.openSession();
        List<Ticket> ticketList = session.createQuery("from entity.Ticket", Ticket.class).list();
        session.close();
        return ticketList;
    }
}