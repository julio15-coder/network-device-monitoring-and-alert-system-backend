package auca.ac.network_device_monitoring_and_alert_system.repository;

import auca.ac.network_device_monitoring_and_alert_system.model.NetworkAdmin;
import auca.ac.network_device_monitoring_and_alert_system.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface NetworkAdminRepository extends JpaRepository<NetworkAdmin, UUID> {

    @Query("SELECT DISTINCT na FROM NetworkAdmin na JOIN na.expertises e WHERE LOWER(e.name) = LOWER(:name)")
    List<NetworkAdmin> findNetworkAdminsByExpertiseName(@Param("name") String name);

    boolean existsByZone(Zone zone);
}