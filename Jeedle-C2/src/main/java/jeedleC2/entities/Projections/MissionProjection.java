package jeedleC2.entities.Projections;

import jeedleC2.entities.Agent;
import jeedleC2.entities.Artifact;
import jeedleC2.entities.Mission;
import jeedleC2.entities.MissionType;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.Collection;

@Projection(name = "mission", types = {Mission.class})
public interface MissionProjection {
    public Long getId();

    public Agent getAgent();

    public MissionType getType();

    public Collection<String> getArgs();

    public LocalDateTime getCreationTime();

    public Boolean getIsSent();

    public Artifact getArtifact();
}
