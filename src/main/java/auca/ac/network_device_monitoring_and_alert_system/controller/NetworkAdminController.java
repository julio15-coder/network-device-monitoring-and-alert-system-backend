package auca.ac.network_device_monitoring_and_alert_system.controller;

import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import auca.ac.network_device_monitoring_and_alert_system.service.NetworkAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/network-admins")
public class NetworkAdminController {

    @Autowired
    private NetworkAdminService networkAdminService;

    @PostMapping("/save")
    public ResponseEntity<?> saveNetworkAdmin(@RequestBody NetworkAdmin networkAdmin) {
        return new ResponseEntity<>(networkAdminService.saveNetworkAdmin(networkAdmin), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNetworkAdmins() {
        return new ResponseEntity<>(networkAdminService.getAllNetworkAdmins(), HttpStatus.OK);
    }

    @GetMapping("/by-expertise")
    public ResponseEntity<?> getNetworkAdminsByExpertise(@RequestParam String name) {
        List<NetworkAdmin> networkAdmins = networkAdminService.getNetworkAdminsByExpertise(name);
        return new ResponseEntity<>(networkAdmins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNetworkAdminById(@PathVariable UUID id) {
        Optional<NetworkAdmin> networkAdmin = networkAdminService.getNetworkAdminById(id);
        if (networkAdmin.isPresent()) {
            return new ResponseEntity<>(networkAdmin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The network admin with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNetworkAdmin(@PathVariable UUID id, @RequestBody NetworkAdmin networkAdmin) {
        String message = networkAdminService.updateNetworkAdmin(id, networkAdmin);
        if (message.equals("Network admin updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNetworkAdmin(@PathVariable UUID id) {
        String message = networkAdminService.deleteNetworkAdmin(id);
        if (message.equals("Network admin deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}