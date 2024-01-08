package com.krishna.casestudy.services;

import com.krishna.casestudy.models.Route;
import com.krishna.casestudy.models.dtos.RouteScheduleDTO;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    List<Route> getAllRoutes();
    Optional<Route> getRouteById(Long id);
    Route createRoute(Route route);
    Route updateRoute(Long id, Route updatedRoute);
    void deleteRoute(Long id);
    Route addStopToRoute(Long routeId, Long stopId);

    Route removeStopFromRoute(Long routeId, Long stopId);

    public List<RouteScheduleDTO> getRouteSchedules(Long routeId);
}
