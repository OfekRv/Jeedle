package jeedleC2.controllers;

import jeedleC2.bl.ArtifactBl;
import jeedleC2.contracts.ArtifactContract;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/artifacts")
public class ArtifactController {
    @Inject
    private ArtifactBl bl;

    @PostMapping
    public void saveArtifact(@RequestBody ArtifactContract artifact) {
        bl.saveArtifact(artifact);
    }
}
