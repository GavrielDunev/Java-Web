package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.OrderEntity;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService, CurrentUser currentUser) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
    }

    @Override
    public void addOrder(OrderServiceModel serviceModel) {
        OrderEntity orderEntity = this.modelMapper.map(serviceModel, OrderEntity.class);
        orderEntity.setEmployee(this.userService.findById(this.currentUser.getId()))
                .setCategory(this.categoryService.findByCategoryEnumName(serviceModel.getCategory()));

        this.orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderViewModel> findAllOrders() {

        return this.orderRepository.findAllOrderByPriceDesc()
                .stream().map(orderEntity -> modelMapper.map(orderEntity, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrderById(Long id) {
        this.orderRepository.deleteById(id);
    }
}
