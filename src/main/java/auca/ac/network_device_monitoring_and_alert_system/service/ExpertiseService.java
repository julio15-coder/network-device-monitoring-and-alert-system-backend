package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.Expertise;
import auca.ac.network_device_monitoring_and_alert_system.repository.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpertiseService {

    @Autowired
    private ExpertiseRepository expertiseRepository;

    public String saveExpertise(Expertise expertise) {
        expertiseRepository.save(expertise);
        return "Expertise saved successfully";
    }

    public List<Expertise> getAllExpertises() {
        return expertiseRepository.findAll();
    }

    public Optional<Expertise> getExpertiseById(UUID id) {
        return expertiseRepository.findById(id);
    }

    public String updateExpertise(UUID id, Expertise expertise) {
        Optional<Expertise> existing = expertiseRepository.findById(id);
        if (existing.isPresent()) {
            Expertise found = existing.get();
            found.setName(expertise.getName());
            expertiseRepository.save(found);
            return "Expertise updated successfully";
        } else {
            return "The expertise with that id does not exist";
        }
    }

    public String deleteExpertise(UUID id) {
        if (expertiseRepository.existsById(id)) {
            expertiseRepository.deleteById(id);
            return "Expertise deleted successfully";
        } else {
            return "The expertise with that id does not exist";
        }
    }

    public List<Expertise> getUnusedExpertises() {
        return expertiseRepository.findUnusedExpertises();
    }
}