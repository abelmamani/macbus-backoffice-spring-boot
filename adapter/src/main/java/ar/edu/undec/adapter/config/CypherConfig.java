package ar.edu.undec.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
@Component
public class CypherConfig {
    @Bean
    Configuration cypherDslConfiguration() {
        return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
    }

}
