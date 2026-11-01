package auca.ac.network_device_monitoring_and_alert_system.service;

import auca.ac.network_device_monitoring_and_alert_system.model.Device;
import auca.ac.network_device_monitoring_and_alert_system.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public String saveDevice(Device device) {
        deviceRepository.save(device);
        return "Device saved successfully";
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }

    public String updateDevice(UUID id, Device device) {
        Optional<Device> existing = deviceRepository.findById(id);
        if (existing.isPresent()) {
            Device found = existing.get();
            found.setHostname(device.getHostname());
            found.setModel(device.getModel());
            found.setIpAddress(device.getIpAddress());
            found.setDeviceType(device.getDeviceType());
            deviceRepository.save(found);
            return "Device updated successfully";
        } else {
            return "The device with that id does not exist";
        }
    }

    public String deleteDevice(UUID id) {
        if (deviceRepository.existsById(id)) {
            deviceRepository.deleteById(id);
            return "Device deleted successfully";
        } else {
            return "The device with that id does not exist";
        }
    }

    public List<Device> getDevicesByDeviceType(String deviceType) {
        return deviceRepository.findByDeviceTypeIgnoreCaseOrderByHostnameAsc(deviceType);
    }
}