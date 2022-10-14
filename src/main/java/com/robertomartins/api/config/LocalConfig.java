package com.robertomartins.api.config;

import com.robertomartins.api.domain.User;
import com.robertomartins.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        User user1 = new User(null, "Roberto Martins", "rm@gmail.com", "1234");
        User user2 = new User(null, "Joao Silva ", "joao@gmail.com", "1234");
        User user3 = new User(null, "Silvia Nogueira", "silvia@gmail.com", "1234");

        repository.saveAll(List.of(user1, user2, user3));

    }
}
