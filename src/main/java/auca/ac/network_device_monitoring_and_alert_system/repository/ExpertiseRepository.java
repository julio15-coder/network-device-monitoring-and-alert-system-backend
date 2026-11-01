package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, UUID> {

    @Query("SELECT e FROM Expertise e WHERE e.networkAdmins IS EMPTY")
    List<Expertise> findUnusedExpertises();
}