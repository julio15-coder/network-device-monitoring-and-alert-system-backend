package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import auca.ac.network_device_monitoring_and_alert_system.repository.AlertRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.DeviceRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.IncidentLogRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.NetworkAdminRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private NetworkAdminRepository networkAdminRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private IncidentLogRepository incidentLogRepository;

    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("devices", deviceRepository.count());
        summary.put("networkAdmins", networkAdminRepository.count());
        summary.put("freeZones", zoneRepository.findFreeZones().size());
        summary.put("alertsToday", alertRepository.countByRaisedDate(LocalDate.now()));
        summary.put("open", alertRepository.countByStatus(AlertStatus.OPEN));
        summary.put("incidentLogs", incidentLogRepository.count());
        return summary;
    }
}