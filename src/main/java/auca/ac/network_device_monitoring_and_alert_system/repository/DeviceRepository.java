package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findByDeviceTypeIgnoreCaseOrderByHostnameAsc(String deviceType);

    @Query("SELECT DISTINCT a.device FROM Alert a WHERE a.networkAdmin.id = :networkAdminId")
    List<Device> findDevicesOfNetworkAdmin(@Param("networkAdminId") UUID networkAdminId);
}