package com.example.exam.service.impl;

import com.example.exam.model.binding.ShipFireBindingModel;
import com.example.exam.model.entity.ShipEntity;
import com.example.exam.model.service.ShipServiceModel;
import com.example.exam.model.view.ShipViewModel;
import com.example.exam.repository.ShipRepository;
import com.example.exam.service.CategoryService;
import com.example.exam.service.ShipService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ModelMapper modelMapper;
    private final ShipRepository shipRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ShipServiceImpl(ModelMapper modelMapper, ShipRepository shipRepository, UserService userService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.shipRepository = shipRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        ShipEntity ship = this.modelMapper.map(shipServiceModel, ShipEntity.class);

        ship.setUser(this.userService.getLoggedInUser())
                .setCategory(this.categoryService.findByCategoryNameEnum(shipServiceModel.getCategory()));

        this.shipRepository.save(ship);
    }

    @Override
    public List<String> getAllOtherUsersShips(Long id) {
       return this.shipRepository.findAllShipNamesWithIdDifferentFrom(id);
    }

    @Override
    public List<ShipViewModel> findAllShipsOrderedById() {
        return this.shipRepository.findAllShipsOrderById()
                .stream().map(shipEntity -> modelMapper.map(shipEntity, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void fire(ShipFireBindingModel shipFireBindingModel) {
        ShipEntity attacker = this.shipRepository.findByName(shipFireBindingModel.getAttacker()).orElse(null);
        ShipEntity defender = this.shipRepository.findByName(shipFireBindingModel.getDefender()).orElse(null);

        if (attacker == null || defender == null) {
            return;
        }

        defender.setHealth(defender.getHealth() - attacker.getPower());

        if (defender.getHealth() <= 0) {
            this.shipRepository.delete(defender);
        } else {
            this.shipRepository.save(defender);
        }
    }

}
