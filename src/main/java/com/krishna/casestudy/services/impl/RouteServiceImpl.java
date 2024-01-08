package com.krishna.casestudy.services.impl;

import com.krishna.casestudy.models.Route;
import com.krishna.casestudy.models.Stop;
import com.krishna.casestudy.models.dtos.RouteScheduleDTO;
import com.krishna.casestudy.repositories.RouteRepository;
import com.krishna.casestudy.repositories.StopRepository;
import com.krishna.casestudy.services.RouteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private StopRepository stopRepository;
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        // You can perform additional validation or logic before saving
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route updatedRoute) {
        if (routeRepository.existsById(id)) {
            updatedRoute.setId(id);
            return routeRepository.save(updatedRoute);
        } else {
            throw new EntityNotFoundException("Route with id " + id + " not found.");
        }
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    @Transactional
    public Route addStopToRoute(Long routeId, Long stopId) {
        Route route = getRouteById(routeId)
                .orElseThrow(() -> new EntityNotFoundException("Route with id " + routeId + " not found."));

        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(() -> new EntityNotFoundException("Stop with id " + stopId + " not found."));

        // Check if the stop is already associated with the route
        if (!route.getStops().contains(stop)) {
            route.getStops().add(stop);
            return routeRepository.save(route);
        } else {
            throw new IllegalStateException("Stop with id " + stopId + " is already associated with Route with id " + routeId + ".");
        }
    }

    @Transactional
    public Route removeStopFromRoute(Long routeId, Long stopId) {
        Route route = getRouteById(routeId)
                .orElseThrow(() -> new EntityNotFoundException("Route with id " + routeId + " not found."));

        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(() -> new EntityNotFoundException("Stop with id " + stopId + " not found."));

        route.getStops().remove(stop);
        return routeRepository.save(route);
    }
    public List<RouteScheduleDTO> getRouteSchedules(Long routeId) {
        return routeRepository.getRouteSchedules(routeId);
    }
}
