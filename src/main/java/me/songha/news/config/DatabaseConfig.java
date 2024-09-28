package me.songha.news.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "me.songha.news.repository.jpa")
@EnableMongoRepositories(basePackages = "me.songha.news.repository.mongo")
public class DatabaseConfig {

}