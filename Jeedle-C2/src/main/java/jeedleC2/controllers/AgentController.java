package jeedleC2.controllers;

import jeedleC2.bl.AgentBl;
import jeedleC2.entities.Agent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class AgentController {
    @Inject
    private AgentBl bl;

    @PostMapping("/register")
    public void registerAgent(@RequestBody Agent agent) {
        bl.registerAgent(agent);
    }
}
