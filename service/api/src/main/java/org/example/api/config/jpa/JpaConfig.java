package org.example.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.example.db")
@EnableJpaRepositories(basePackages = "org.example.db")
public class JpaConfig {

}
