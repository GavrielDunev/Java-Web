package com.example.exam.service;

import com.example.exam.model.entity.UserEntity;
import com.example.exam.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel user);

    void registerUser(UserServiceModel userServiceModel);

    UserEntity getLoggedInUser();

    List<String> getAllLoggedInUserShips();

}
