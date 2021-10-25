package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.RouteEntity;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public List<RouteViewModel> findAllRouteViews() {
        return this.routeRepository.findAll()
                .stream().map(routeEntity -> {
                    RouteViewModel routeViewModel = modelMapper.map(routeEntity, RouteViewModel.class);
                    routeViewModel.setPictureUrl(routeEntity.getPictures().isEmpty() ? "/images/pic4.jpg"
                            : routeEntity.getPictures().stream().findFirst().get().getUrl());

                    return routeViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {
        RouteEntity route = this.modelMapper.map(routeServiceModel, RouteEntity.class);
        route.setAuthor(this.userService.findCurrentUserEntity());
        route.setCategories(routeServiceModel.getCategories()
                .stream()
                .map(this.categoryService::findCategoryByName)
                .collect(Collectors.toList()));


        this.routeRepository.save(route);
    }

    @Override
    public RouteDetailsViewModel findById(Long id) {

        return this.routeRepository.findById(id)
                .map(routeEntity -> this.modelMapper.map(routeEntity, RouteDetailsViewModel.class))
                .orElse(null);
    }
}
