package auca.ac.network_device_monitoring_and_alert_system.controller;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import auca.ac.network_device_monitoring_and_alert_system.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAlert(@RequestBody Alert alert) {
        String message = alertService.saveAlert(alert);
        if (message.equals("Alert saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Alert date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (message.equals("Network admin is already assigned an alert on that date")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAlerts() {
        return new ResponseEntity<>(alertService.getAllAlerts(), HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<?> getAlertsByStatus(@RequestParam AlertStatus status) {
        List<Alert> alerts = alertService.getAlertsByStatus(status);
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<?> getAlertsBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<Alert> alerts = alertService.getAlertsBetween(startDate, endDate);
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertById(@PathVariable UUID id) {
        Optional<Alert> alert = alertService.getAlertById(id);
        if (alert.isPresent()) {
            return new ResponseEntity<>(alert.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The alert with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAlert(@PathVariable UUID id, @RequestBody Alert alert) {
        String message = alertService.updateAlert(id, alert);
        if (message.equals("Alert updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateAlertStatus(@PathVariable UUID id, @RequestParam AlertStatus status) {
        String message = alertService.updateAlertStatus(id, status);
        if (message.equals("Alert status updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The alert with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable UUID id) {
        String message = alertService.deleteAlert(id);
        if (message.equals("Alert deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That alert has incident logs, delete them first")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}