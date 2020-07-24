package jeedleC2.bl;

import jeedleC2.entities.Mission;
import jeedleC2.repositories.MissionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Comparator;
import java.util.Optional;

@Named
public class MissionBl {
    @Inject
    private MissionRepository repository;

    public Mission nextAgentMission(Long agentId) {
        Optional<Mission> nextAgentMission = repository.findByAgentId(agentId).stream().sorted(Comparator.comparing(Mission::getCreationTime)).findFirst();
        if (nextAgentMission.isPresent()) {
            return nextAgentMission.get();
        }
        return null;
    }
}
