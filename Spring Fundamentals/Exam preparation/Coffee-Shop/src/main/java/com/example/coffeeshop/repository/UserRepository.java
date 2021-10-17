package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}
