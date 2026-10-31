package auca.ac.network_device_monitoring_and_alert_system.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "device")
public class Device {

    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String hostname;

    private String model;
    @Column(unique = true)
    private String ipAddress;

    private String deviceType;

    public Device() {

    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }
    public String getHostname() { return hostname; }
    
    public void setHostname(String hostname) { this.hostname = hostname; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
}