package jeedleC2.controllers;

import jeedleC2.bl.MissionBl;
import jeedleC2.entities.Mission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class MissionController {
    @Inject
    private MissionBl bl;

    @GetMapping
    public Mission nextMission(@RequestParam Long agentId) {
        return bl.nextAgentMission(agentId);
    }
}
