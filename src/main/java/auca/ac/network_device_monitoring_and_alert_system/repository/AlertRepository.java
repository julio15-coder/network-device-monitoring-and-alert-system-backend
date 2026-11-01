package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AlertRepository extends JpaRepository<Alert, UUID> {

    List<Alert> findByStatusOrderByRaisedDateAsc(AlertStatus status);

    List<Alert> findByRaisedDateBetweenOrderByRaisedDateAsc(LocalDate start, LocalDate end);

    boolean existsByNetworkAdminAndRaisedDateAndStatusNot(NetworkAdmin networkAdmin, LocalDate raisedDate, AlertStatus status);
}