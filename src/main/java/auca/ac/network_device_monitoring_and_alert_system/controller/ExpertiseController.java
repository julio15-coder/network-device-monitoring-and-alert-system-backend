package auca.ac.network_device_monitoring_and_alert_system.controller;

import auca.ac.network_device_monitoring_and_alert_system.model.Expertise;
import auca.ac.network_device_monitoring_and_alert_system.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/expertises")
public class ExpertiseController {

    @Autowired
    private ExpertiseService expertiseService;

    @PostMapping("/save")
    public ResponseEntity<?> saveExpertise(@RequestBody Expertise expertise) {
        return new ResponseEntity<>(expertiseService.saveExpertise(expertise), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpertises() {
        return new ResponseEntity<>(expertiseService.getAllExpertises(), HttpStatus.OK);
    }

    @GetMapping("/unused")
    public ResponseEntity<?> getUnusedExpertises() {
        List<Expertise> expertises = expertiseService.getUnusedExpertises();
        return new ResponseEntity<>(expertises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpertiseById(@PathVariable UUID id) {
        Optional<Expertise> expertise = expertiseService.getExpertiseById(id);
        if (expertise.isPresent()) {
            return new ResponseEntity<>(expertise.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The expertise with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExpertise(@PathVariable UUID id, @RequestBody Expertise expertise) {
        String message = expertiseService.updateExpertise(id, expertise);
        if (message.equals("Expertise updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpertise(@PathVariable UUID id) {
        String message = expertiseService.deleteExpertise(id);
        if (message.equals("Expertise deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}