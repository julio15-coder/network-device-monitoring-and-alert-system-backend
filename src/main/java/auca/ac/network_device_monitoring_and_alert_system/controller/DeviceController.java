package auca.ac.network_device_monitoring_and_alert_system.controller;

import auca.ac.network_device_monitoring_and_alert_system.model.Device;
import auca.ac.network_device_monitoring_and_alert_system.service.DeviceService;
import auca.ac.network_device_monitoring_and_alert_system.service.NetworkAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private NetworkAdminService networkAdminService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDevice(@RequestBody Device device) {
        return new ResponseEntity<>(deviceService.saveDevice(device), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDevices() {
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }

    @GetMapping("/by-device-type")
    public ResponseEntity<?> getDevicesByDeviceType(@RequestParam String deviceType) {
        List<Device> devices = deviceService.getDevicesByDeviceType(deviceType);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/of-network-admin/{networkAdminId}")
    public ResponseEntity<?> getDevicesOfNetworkAdmin(@PathVariable UUID networkAdminId) {
        if (networkAdminService.getNetworkAdminById(networkAdminId).isEmpty()) {
            return new ResponseEntity<>("The network admin with that id does not exist", HttpStatus.NOT_FOUND);
        } else {
            List<Device> devices = deviceService.getDevicesOfNetworkAdmin(networkAdminId);
            return new ResponseEntity<>(devices, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable UUID id) {
        Optional<Device> device = deviceService.getDeviceById(id);
        if (device.isPresent()) {
            return new ResponseEntity<>(device.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The device with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable UUID id, @RequestBody Device device) {
        String message = deviceService.updateDevice(id, device);
        if (message.equals("Device updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable UUID id) {
        String message = deviceService.deleteDevice(id);
        if (message.equals("Device deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}