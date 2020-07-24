package jeedleC2.bl;

import jeedleC2.entities.Agent;
import jeedleC2.repositories.AgentRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;

@Named
public class AgentBl {
    @Inject
    private AgentRepository repository;

    public void registerAgent(Agent agent) {
        agent.setLastActive(LocalDateTime.now());
        repository.save(agent);
    }
}
