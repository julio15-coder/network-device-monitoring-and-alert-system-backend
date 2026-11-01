package auca.ac.network_device_monitoring_and_alert_system.controller;

import auca.ac.network_device_monitoring_and_alert_system.model.IncidentLog;
import auca.ac.network_device_monitoring_and_alert_system.service.IncidentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/incident-logs")
public class IncidentLogController {

    @Autowired
    private IncidentLogService incidentLogService;

    @PostMapping("/save")
    public ResponseEntity<?> saveIncidentLog(@RequestParam UUID alertId, @RequestBody IncidentLog incidentLog) {
        String message = incidentLogService.saveIncidentLog(alertId, incidentLog);
        if (message.equals("Incident log saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Incident logs are only for RESOLVED alerts")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncidentLogs() {
        return new ResponseEntity<>(incidentLogService.getAllIncidentLogs(), HttpStatus.OK);
    }

    @GetMapping("/device/{ipAddress}")
    public ResponseEntity<?> getIncidentLogsOfDevice(@PathVariable String ipAddress) {
        List<IncidentLog> incidentLogs = incidentLogService.getIncidentLogsOfDevice(ipAddress);
        return new ResponseEntity<>(incidentLogs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncidentLog(@PathVariable UUID id) {
        String message = incidentLogService.deleteIncidentLog(id);
        if (message.equals("Incident log deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}