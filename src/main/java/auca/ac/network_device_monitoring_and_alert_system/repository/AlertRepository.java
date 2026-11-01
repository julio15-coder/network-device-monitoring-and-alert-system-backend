package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Alert;
import auca.ac.network_device_monitoring_and_alert_system.model.AlertStatus;
import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AlertRepository extends JpaRepository<Alert, UUID> {

    List<Alert> findByStatusOrderByRaisedDateAsc(AlertStatus status);

    List<Alert> findByRaisedDateBetweenOrderByRaisedDateAsc(LocalDate start, LocalDate end);

    boolean existsByNetworkAdminAndRaisedDateAndStatusNot(NetworkAdmin networkAdmin, LocalDate raisedDate, AlertStatus status);

    @Query("SELECT a.status, COUNT(a) FROM Alert a GROUP BY a.status")
    List<Object[]> countAlertsByStatus();

    @Modifying
    @Query("UPDATE Alert a SET a.status = :dismissed WHERE a.networkAdmin.id = :networkAdminId AND a.raisedDate = :date AND a.status <> :resolved")
    int cancelDay(@Param("networkAdminId") UUID networkAdminId,
                  @Param("date") LocalDate date,
                  @Param("dismissed") AlertStatus dismissed,
                  @Param("resolved") AlertStatus resolved);

    long countByRaisedDate(LocalDate raisedDate);

    long countByStatus(AlertStatus status);
}