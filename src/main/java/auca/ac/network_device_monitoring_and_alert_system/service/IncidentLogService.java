package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import auca.ac.network_device_monitoring_and_alert_system.model.IncidentLog;
import auca.ac.network_device_monitoring_and_alert_system.repository.AlertRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.IncidentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IncidentLogService {

    @Autowired
    private IncidentLogRepository incidentLogRepository;

    @Autowired
    private AlertRepository alertRepository;

    public String saveIncidentLog(UUID alertId, IncidentLog incidentLog) {
        Optional<Alert> alert = alertRepository.findById(alertId);
        if (alert.isEmpty()) {
            return "The alert with that id does not exist";
        }
        if (alert.get().getStatus() != AlertStatus.RESOLVED) {
            return "Incident logs are only for RESOLVED alerts";
        }
        incidentLog.setLoggedDate(LocalDate.now());
        incidentLog.setAlert(alert.get());
        incidentLogRepository.save(incidentLog);
        return "Incident log saved successfully";
    }

    public List<IncidentLog> getAllIncidentLogs() {
        return incidentLogRepository.findAll();
    }

    public List<IncidentLog> getIncidentLogsOfDevice(String ipAddress) {
        return incidentLogRepository.findByDeviceIpAddress(ipAddress);
    }

    public String deleteIncidentLog(UUID id) {
        if (incidentLogRepository.existsById(id)) {
            incidentLogRepository.deleteById(id);
            return "Incident log deleted successfully";
        } else {
            return "The incident log with that id does not exist";
        }
    }
}