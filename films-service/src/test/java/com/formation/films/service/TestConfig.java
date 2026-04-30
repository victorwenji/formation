package com.formation.films.service;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan(basePackages = "com.formation.films.domain.entities")
@EnableJpaRepositories(basePackages = "com.formation.films.service.repositories")
public class TestConfig {}