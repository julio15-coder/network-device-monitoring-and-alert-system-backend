package auca.ac.network_device_monitoring_and_alert_system.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "network_admin")
public class NetworkAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = true)
    private Zone zone;

    @ManyToMany
    @JoinTable(
            name = "network_admin_expertise",
            joinColumns = @JoinColumn(name = "network_admin_id"),
            inverseJoinColumns = @JoinColumn(name = "expertise_id")
    )
    private List<Expertise> expertises = new ArrayList<>();

    public NetworkAdmin() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
    public List<Expertise> getExpertises() { return expertises; }
    public void setExpertises(List<Expertise> expertises) { this.expertises = expertises; }
}