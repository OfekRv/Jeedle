package jeedleC2.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.IOException;

@RestController
public class BeaconController {
    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    @Inject
    private ResourceLoader resourceLoader;

    @GetMapping("downloadBeacon")
    public ResponseEntity<Resource> downloadBeacon() throws IOException {
        Resource fileResource = resourceLoader.getResource("classpath:Beacons/Agent.jar");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"Beacon\"")
                .contentLength(fileResource.contentLength()).contentType(MediaType.parseMediaType(APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileResource.getInputStream()));
    }
}