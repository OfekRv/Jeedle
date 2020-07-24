package jeedleC2.bl;

import jeedleC2.contracts.ArtifactContract;
import jeedleC2.entities.Artifact;
import jeedleC2.repositories.AgentRepository;
import jeedleC2.repositories.ArtifactRepository;
import jeedleC2.repositories.MissionRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ArtifactBl {
    @Inject
    private ArtifactRepository repository;
    @Inject
    private MissionRepository missionRepository;
    @Inject
    private AgentRepository agentRepository;

    public void saveArtifact(ArtifactContract rawArtifact) {
        Artifact artifact = new Artifact(rawArtifact.getContent(), agentRepository.findById(rawArtifact.getCreatingAgentId()).get());
        artifact.setCreatingMission(missionRepository.findById(rawArtifact.getCreatingMissionId()).get());
        repository.save(artifact);
    }
}
