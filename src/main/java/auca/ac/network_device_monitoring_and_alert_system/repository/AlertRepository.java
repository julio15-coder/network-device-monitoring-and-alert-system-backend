package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Repository
public interface AlertRepository extends JpaRepository<Alert, UUID> {

    List<Alert> findByStatusOrderByRaisedDateAsc(AlertStatus status);

    List<Alert> findByRaisedDateBetweenOrderByRaisedDateAsc(LocalDate start, LocalDate end);
}