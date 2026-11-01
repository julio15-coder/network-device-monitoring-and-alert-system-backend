package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import auca.ac.network_device_monitoring_and_alert_system.model.Device;
import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import auca.ac.network_device_monitoring_and_alert_system.repository.AlertRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.DeviceRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.NetworkAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
private DeviceRepository deviceRepository;

    @Autowired
    private NetworkAdminRepository networkAdminRepository;
public String saveAlert(Alert alert) {
        Optional<Device> device = findDevice(alert);
        if (device.isEmpty()) {
            return "The device with that id does not exist";
        }
        Optional<NetworkAdmin> networkAdmin = findNetworkAdmin(alert);
        if (networkAdmin.isEmpty()) {
            return "The network admin with that id does not exist";
        }
        alert.setDevice(device.get());
        alert.setNetworkAdmin(networkAdmin.get());
        alertRepository.save(alert);
        return "Alert saved successfully";
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Optional<Alert> getAlertById(UUID id) {
        return alertRepository.findById(id);
    }

    public String updateAlert(UUID id, Alert alert) {
        Optional<Alert> existing = alertRepository.findById(id);
        if (existing.isEmpty()) {
            return "The alert with that id does not exist";
        }
        Optional<Device> device = findDevice(alert);
        if (device.isEmpty()) {
            return "The device with that id does not exist";
        }
        Optional<NetworkAdmin> networkAdmin = findNetworkAdmin(alert);
        if (networkAdmin.isEmpty()) {
            return "The network admin with that id does not exist";
        }
        Alert found = existing.get();
        found.setRaisedDate(alert.getRaisedDate());
        found.setStatus(alert.getStatus());
        found.setSeverity(alert.getSeverity());
        found.setMessage(alert.getMessage());
        found.setDevice(device.get());
        found.setNetworkAdmin(networkAdmin.get());
        alertRepository.save(found);
        return "Alert updated successfully";
    }

    public String deleteAlert(UUID id) {
        if (alertRepository.existsById(id)) {
            alertRepository.deleteById(id);
            return "Alert deleted successfully";
        } else {
            return "The alert with that id does not exist";
        }
    }

    public List<Alert> getAlertsByStatus(AlertStatus status) {
        return alertRepository.findByStatusOrderByRaisedDateAsc(status);
    }

    private Optional<Device> findDevice(Alert alert) {
        if (alert.getDevice() == null || alert.getDevice().getId() == null) {
            return Optional.empty();
        }
        return deviceRepository.findById(alert.getDevice().getId());
    }

    private Optional<NetworkAdmin> findNetworkAdmin(Alert alert) {
        if (alert.getNetworkAdmin() == null || alert.getNetworkAdmin().getId() == null)
            
            {
            return Optional.empty();
        
        }
        
        return networkAdminRepository.findById(alert.getNetworkAdmin().getId());
    }



}