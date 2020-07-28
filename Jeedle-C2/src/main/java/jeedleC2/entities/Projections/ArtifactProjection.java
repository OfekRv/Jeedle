package jeedleC2.entities.Projections;

import jeedleC2.entities.Agent;
import jeedleC2.entities.Artifact;
import jeedleC2.entities.Mission;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "artifact", types = {Artifact.class})
public interface ArtifactProjection {
    public Long getId();

    public String getContent();

    public Mission getCreatingMission();

    public Agent getCreatingAgent();
}
