package jeedleC2.repositories;

import jeedleC2.entities.Mission;
import jeedleC2.entities.Projections.MissionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(excerptProjection = MissionProjection.class)
public interface MissionRepository extends JpaRepository<Mission, Long> {
    public Collection<Mission> findByAgentId(String agentId);
}
