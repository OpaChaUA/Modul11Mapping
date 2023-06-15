import services.ClientCrudService;
import services.HibernateUtil;
import services.PlanetCrudService;
import services.TicketCrudService;
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
        ticketCrudService.create(clientCrudService.getById(6),
                planetCrudService.getById("PL4"),
                planetCrudService.getById("PL5"));
        System.out.println(ticketCrudService.getById(11));
        ticketCrudService.update(clientCrudService.getById(7), 11);
        ticketCrudService.delete(8);
        System.out.println(ticketCrudService.getTickets());
    }

    private static void ticketCrudServiceNullCheck() {
        ticketCrudService.create(null,
                planetCrudService.getById("PL1"),
                planetCrudService.getById("PL2"));

        ticketCrudService.create(clientCrudService.getById(3),
                null,
                planetCrudService.getById("PL2"));
    }
}