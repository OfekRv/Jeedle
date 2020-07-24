package jeedleC2.repositories;

import jeedleC2.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    public Collection<Mission> findByAgentId(Long agentId);
}
