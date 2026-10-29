package auca.ac.network_device_monitoring_and_alert_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate raisedDate;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private String severity;

    private String message;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "network_admin_id")
    private NetworkAdmin networkAdmin;

    public Alert() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getRaisedDate() { return raisedDate; }
    public void setRaisedDate(LocalDate raisedDate) { this.raisedDate = raisedDate; }
    public AlertStatus getStatus() { return status; }
    public void setStatus(AlertStatus status) { this.status = status; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }
    public NetworkAdmin getNetworkAdmin() { return networkAdmin; }
    public void setNetworkAdmin(NetworkAdmin networkAdmin) { this.networkAdmin = networkAdmin; }
}