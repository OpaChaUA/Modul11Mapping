package entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

@Entity
@Table(name="Ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Generated
    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;
}