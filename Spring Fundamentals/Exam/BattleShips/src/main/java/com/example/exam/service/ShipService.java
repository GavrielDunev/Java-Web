package com.example.exam.service;

import com.example.exam.model.binding.ShipFireBindingModel;
import com.example.exam.model.service.ShipServiceModel;
import com.example.exam.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(ShipServiceModel shipServiceModel);

    List<String> getAllOtherUsersShips(Long id);

    List<ShipViewModel> findAllShipsOrderedById();


    void fire(ShipFireBindingModel shipFireBindingModel);
}
