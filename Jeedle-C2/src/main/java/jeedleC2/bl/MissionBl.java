package jeedleC2.bl;

import jeedleC2.entities.Mission;
import jeedleC2.repositories.MissionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Optional;

@Named
public class MissionBl {
    @Inject
    private MissionRepository repository;

    @Transactional
    public Mission nextAgentMission(String agentId) {
        Optional<Mission> nextAgentMission = repository.findByAgentId(agentId).stream()
                .filter(mission -> !mission.getIsSent())
                .sorted(Comparator.comparing(Mission::getCreationTime))
                .findFirst();
        if (nextAgentMission.isPresent()) {
            nextAgentMission.get().setIsSent(true);
            return nextAgentMission.get();
        }
        return null;
    }
}
