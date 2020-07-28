package jeedleC2.repositories;

import jeedleC2.entities.Artifact;
import jeedleC2.entities.Projections.ArtifactProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ArtifactProjection.class)
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
}
