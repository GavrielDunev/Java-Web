package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;

public interface UserService {

    void registerUser(UserServiceModel serviceModel);

    boolean isEmailFree(String email);

    boolean isUsernameFree(String username);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel serviceModel);

    UserEntity findById(Long id);
}
