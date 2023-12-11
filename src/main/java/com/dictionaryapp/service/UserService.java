package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(UserRegisterDTO model);

    void login(UserLoginDTO model);

    User GetUserFromUsername(String username);
}
