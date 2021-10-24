package com.example.exam.repository;

import com.example.exam.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    @Query("SELECT s.name FROM ShipEntity s WHERE s.user.id NOT IN (:id)")
    List<String> findAllShipNamesWithIdDifferentFrom(Long id);

    @Query("SELECT s FROM ShipEntity s ORDER BY s.id")
    List<ShipEntity> findAllShipsOrderById();

    Optional<ShipEntity> findByName(String name);
}
