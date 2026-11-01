package auca.ac.network_device_monitoring_and_alert_system.controller;

import auca.ac.network_device_monitoring_and_alert_system.model.Zone;
import auca.ac.network_device_monitoring_and_alert_system.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/save")
    public ResponseEntity<?> saveZone(@RequestBody Zone zone) {
        return new ResponseEntity<>(zoneService.saveZone(zone), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllZones() {
        return new ResponseEntity<>(zoneService.getAllZones(), HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<?> getFreeZones() {
        List<Zone> zones = zoneService.getFreeZones();
        return new ResponseEntity<>(zones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getZoneById(@PathVariable UUID id) {
        Optional<Zone> zone = zoneService.getZoneById(id);
        if (zone.isPresent()) {
            return new ResponseEntity<>(zone.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The zone with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateZone(@PathVariable UUID id, @RequestBody Zone zone) {
        String message = zoneService.updateZone(id, zone);
        if (message.equals("Zone updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable UUID id) {
        String message = zoneService.deleteZone(id);
        if (message.equals("Zone deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}