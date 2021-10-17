package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel serviceModel) {
        this.userRepository.save(modelMapper.map(serviceModel, UserEntity.class));
    }
}
