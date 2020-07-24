package jeedleC2.configuration;

import jeedleC2.entities.Agent;
import jeedleC2.entities.Artifact;
import jeedleC2.entities.Mission;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringDataRestConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Agent.class);
        config.exposeIdsFor(Mission.class);
        config.exposeIdsFor(Artifact.class);
    }
}