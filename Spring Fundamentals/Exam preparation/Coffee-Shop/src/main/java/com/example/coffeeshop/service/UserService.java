package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    void registerUser(UserServiceModel serviceModel);

    boolean isEmailFree(String email);

    boolean isUsernameFree(String username);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel serviceModel);

    UserEntity findById(Long id);

    List<UserViewModel> findAllOrderByCountOfOrders();
}
