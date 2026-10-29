package auca.ac.network_device_monitoring_and_alert_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity 
@Table( name = "expertise")
public class Expertise {

    @Id 
    @GeneratedValue (
        strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "expertises")
    @JsonIgnore
    private List<NetworkAdmin> networkAdmins = new ArrayList<>();

    public Expertise() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<NetworkAdmin> getNetworkAdmins() { return networkAdmins; }
    public void setNetworkAdmins(List<NetworkAdmin> networkAdmins) { this.networkAdmins = networkAdmins; }
}



