package com.example.pathfinder.service;

import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;

import java.util.List;

public interface RouteService {
    List<RouteViewModel> findAllRouteViews();

    void addNewRoute(RouteServiceModel routeServiceModel);

    RouteDetailsViewModel findById(Long id);
}
