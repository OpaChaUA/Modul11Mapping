import Servais.ClientCrudService;
import Servais.HibernateUtil;
import Servais.PlanetCrudService;
import Servais.TicketCrudService;
import org.flywaydb.core.Flyway;

import java.util.ResourceBundle;

public class App {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("hibernate");
    private static final String JDBC_URL = "hibernate.connection.url";
    private static final ClientCrudService clientCrudService = new ClientCrudService();
    private static final PlanetCrudService planetCrudService = new PlanetCrudService();
    private static final TicketCrudService ticketCrudService = new TicketCrudService();

    public static void main(String[] args) {
        Flyway.configure()
                .dataSource(resourceBundle.getString(JDBC_URL), null, null)
                .load()
                .migrate();

        ticketCrudServiceCheck();

        ticketCrudServiceNullCheck();

        HibernateUtil.getInstance().close();
    }

    private static void ticketCrudServiceCheck() {
        ticketCrudService.createTicket(clientCrudService.getClientById(6),
                planetCrudService.getPlanetById("PL4"),
                planetCrudService.getPlanetById("PL5"));
        System.out.println(ticketCrudService.getTicketById(11));
        ticketCrudService.updateTicket(clientCrudService.getClientById(7), 11);
        ticketCrudService.deleteTicket(8);
        System.out.println(ticketCrudService.getAllTickets());
    }

    private static void ticketCrudServiceNullCheck() {
        ticketCrudService.createTicket(null,
                planetCrudService.getPlanetById("PL1"),
                planetCrudService.getPlanetById("PL2"));

        ticketCrudService.createTicket(clientCrudService.getClientById(3),
                null,
                planetCrudService.getPlanetById("PL2"));
    }
}