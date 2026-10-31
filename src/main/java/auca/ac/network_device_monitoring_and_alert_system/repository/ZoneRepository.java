package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    
}