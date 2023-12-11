package com.dictionaryapp.util;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter

@SessionScope
@Component
public class LoggedUser {

    private String username;

    private boolean isLogged;

    public LoggedUser() {
    }

    public void LogIn(String username){
        this.username = username;
        isLogged = true;
    }
    public void Logout(){
        username = null;
        isLogged = false;
    }
}
