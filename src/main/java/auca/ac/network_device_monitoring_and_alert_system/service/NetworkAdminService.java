package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.Expertise;
import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import auca.ac.network_device_monitoring_and_alert_system.model.Zone;
import auca.ac.network_device_monitoring_and_alert_system.repository.ExpertiseRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.NetworkAdminRepository;
import auca.ac.network_device_monitoring_and_alert_system.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NetworkAdminService {

    @Autowired
    private NetworkAdminRepository networkAdminRepository;

    @Autowired
    private ExpertiseRepository expertiseRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    public String saveNetworkAdmin(NetworkAdmin networkAdmin) {
        if (networkAdmin.getExpertises() == null) {
            networkAdmin.setExpertises(new ArrayList<>());
        }
        networkAdminRepository.save(networkAdmin);
        return "Network admin saved successfully";
    }

    public List<NetworkAdmin> getAllNetworkAdmins() {
        return networkAdminRepository.findAll();
    }

    public Optional<NetworkAdmin> getNetworkAdminById(UUID id) {
        return networkAdminRepository.findById(id);
    }

    public String updateNetworkAdmin(UUID id, NetworkAdmin networkAdmin) {
        Optional<NetworkAdmin> existing = networkAdminRepository.findById(id);
        if (existing.isPresent()) {
            NetworkAdmin found = existing.get();
            found.setFirstName(networkAdmin.getFirstName());
            found.setLastName(networkAdmin.getLastName());
            found.setEmail(networkAdmin.getEmail());
            found.setZone(networkAdmin.getZone());
            if (networkAdmin.getExpertises() != null && !networkAdmin.getExpertises().isEmpty()) {
                found.setExpertises(networkAdmin.getExpertises());
            }
            networkAdminRepository.save(found);
            return "Network admin updated successfully";
        } else {
            return "The network admin with that id does not exist";
        }
    }

    public String deleteNetworkAdmin(UUID id) {
        if (networkAdminRepository.existsById(id)) {
            networkAdminRepository.deleteById(id);
            return "Network admin deleted successfully";
        } else {
            return "The network admin with that id does not exist";
        }
    }

    public List<NetworkAdmin> getNetworkAdminsByExpertise(String name) {
        return networkAdminRepository.findNetworkAdminsByExpertiseName(name);
    }

    @Transactional
    public String addExpertiseToNetworkAdmin(UUID networkAdminId, UUID expertiseId) {
        Optional<NetworkAdmin> networkAdmin = networkAdminRepository.findById(networkAdminId);
        if (networkAdmin.isEmpty()) {
            return "The network admin with that id does not exist";
        }
        Optional<Expertise> expertise = expertiseRepository.findById(expertiseId);
        if (expertise.isEmpty()) {
            return "That expertise does not exist";
        }
        NetworkAdmin found = networkAdmin.get();
        if (found.getExpertises() == null) {
            found.setExpertises(new ArrayList<>());
        }
        if (found.getExpertises().contains(expertise.get())) {
            return "The network admin already has that expertise";
        }
        found.getExpertises().add(expertise.get());
        networkAdminRepository.save(found);
        return "Expertise added to the network admin";
    }

    public String changeZone(UUID networkAdminId, int zoneNumber) {
        Optional<NetworkAdmin> networkAdmin = networkAdminRepository.findById(networkAdminId);
        if (networkAdmin.isEmpty()) {
            return "The network admin with that id does not exist";
        }
        Optional<Zone> zone = zoneRepository.findByZoneNumber(zoneNumber);
        if (zone.isEmpty()) {
            return "The zone with that zoneNumber does not exist";
        }
        NetworkAdmin found = networkAdmin.get();
        boolean alreadyHisZone = found.getZone() != null && found.getZone().getId().equals(zone.get().getId());
        if (!alreadyHisZone && networkAdminRepository.existsByZone(zone.get())) {
            return "That zone is taken by another network admin";
        }
        found.setZone(zone.get());
        networkAdminRepository.save(found);
        return "Zone changed successfully";
    }
}