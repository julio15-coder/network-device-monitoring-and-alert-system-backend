package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    @Query("SELECT z FROM Zone z WHERE NOT EXISTS (SELECT na FROM NetworkAdmin na WHERE na.zone = z) ORDER BY z.zoneNumber ASC")
    List<Zone> findFreeZones();

    Optional<Zone> findByZoneNumber(int zoneNumber);
}