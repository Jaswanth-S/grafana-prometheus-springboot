package com.stackroute.mongodbdemo.config;

import com.stackroute.mongodbdemo.document.Users;
import com.stackroute.mongodbdemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {
            userRepository.save(new Users(1, "Jaswanth", "teamA", 3000L));
            userRepository.save(new Users(2, "Sai", "teamB", 2000L));
        };
    }

}
