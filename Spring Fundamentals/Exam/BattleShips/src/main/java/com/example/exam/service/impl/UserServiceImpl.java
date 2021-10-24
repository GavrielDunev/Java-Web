package com.example.exam.service.impl;

import com.example.exam.model.entity.ShipEntity;
import com.example.exam.model.entity.UserEntity;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.repository.UserRepository;
import com.example.exam.service.ShipService;
import com.example.exam.service.UserService;
import com.example.exam.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(UserServiceModel user) {
        this.currentUser.setId(user.getId())
                .setUsername(user.getUsername());
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        this.userRepository.save(this.modelMapper.map(userServiceModel, UserEntity.class));
    }

    @Override
    public UserEntity getLoggedInUser() {
        return this.userRepository.findById(this.currentUser.getId()).orElse(null);
    }

    @Override
    public List<String> getAllLoggedInUserShips() {
        UserEntity loggedInUser = getLoggedInUser();

        return loggedInUser.getShips()
                .stream().map(ShipEntity::getName)
                .collect(Collectors.toList());
    }

}
