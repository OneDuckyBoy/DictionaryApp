package com.dictionaryapp.config;


import com.dictionaryapp.util.LoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    private static LoggedUser loggedUser = new LoggedUser();
    public LoggedUser loggedUser(){
        return this.loggedUser;
    }
}
