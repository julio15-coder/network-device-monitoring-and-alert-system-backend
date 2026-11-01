package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.IncidentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface IncidentLogRepository extends JpaRepository<IncidentLog, UUID> {

    @Query("SELECT d FROM IncidentLog d WHERE d.alert.device.ipAddress = :ipAddress ORDER BY d.loggedDate DESC")
    List<IncidentLog> findByDeviceIpAddress(@Param("ipAddress") String ipAddress);

    boolean existsByAlert(Alert alert);
}