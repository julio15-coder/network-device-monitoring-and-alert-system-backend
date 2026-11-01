package auca.ac.network_device_monitoring_and_alert_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "incident_log")
public class IncidentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String rootCause;

    private String notes;

    private int downtimeMinutes;

    private LocalDate loggedDate;

    @ManyToOne
    @JoinColumn(name = "alert_id")
    private Alert alert;

    public IncidentLog() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public int getDowntimeMinutes() { return downtimeMinutes; }
    public void setDowntimeMinutes(int downtimeMinutes) { this.downtimeMinutes = downtimeMinutes; }
    public LocalDate getLoggedDate() { return loggedDate; }
    public void setLoggedDate(LocalDate loggedDate) { this.loggedDate = loggedDate; }
    public Alert getAlert() { return alert; }
    public void setAlert(Alert alert) { this.alert = alert; }
}