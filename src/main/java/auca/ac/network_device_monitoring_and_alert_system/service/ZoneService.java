package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.Zone;
import auca.ac.network_device_monitoring_and_alert_system.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public String saveZone(Zone zone) {
        zoneRepository.save(zone);
        return "Zone saved successfully";
    }

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> getZoneById(UUID id) {
        return zoneRepository.findById(id);
    }

    public String updateZone(UUID id, Zone zone) {
        Optional<Zone> existing = zoneRepository.findById(id);
        if (existing.isPresent()) {
            Zone found = existing.get();
            found.setZoneNumber(zone.getZoneNumber());
            found.setName(zone.getName());
            zoneRepository.save(found);
            return "Zone updated successfully";
        } else {
            return "The zone with that id does not exist";
        }
    }

    public String deleteZone(UUID id) {
        if (zoneRepository.existsById(id)) {
            zoneRepository.deleteById(id);
            return "Zone deleted successfully";
        } else {
            return "The zone with that id does not exist";
        }
    }

    public List<Zone> getFreeZones() {
        return zoneRepository.findFreeZones();
    }
}