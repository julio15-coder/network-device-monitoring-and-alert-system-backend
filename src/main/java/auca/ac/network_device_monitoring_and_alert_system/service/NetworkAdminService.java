package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import auca.ac.network_device_monitoring_and_alert_system.repository.NetworkAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NetworkAdminService {

    @Autowired
    private NetworkAdminRepository networkAdminRepository;

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
}