package com.krishna.casestudy.controllers;

import com.krishna.casestudy.models.Route;
import com.krishna.casestudy.models.dtos.RouteScheduleDTO;
import com.krishna.casestudy.services.RouteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        try {
            return routeService.getRouteById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception or handle it according to your requirements
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route updatedRoute) {
        try {
            Route updated = routeService.updateRoute(id, updatedRoute);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Log the exception or handle it according to your requirements
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
    // Create a mapping between a stop and a route
    @PostMapping("/{routeId}/stops/{stopId}")
    public ResponseEntity<?> addStopToRoute(@PathVariable Long routeId, @PathVariable Long stopId) {
        try {
            Route updatedRoute = routeService.addStopToRoute(routeId, stopId);
            return ResponseEntity.ok(updatedRoute);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Stop is already associated with the route.");
        } catch (Exception e) {
            // Log the exception or handle it according to your requirements
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Remove a mapping between a stop and a route
    @DeleteMapping("/{routeId}/stops/{stopId}")
    public ResponseEntity<Route> removeStopFromRoute(@PathVariable Long routeId, @PathVariable Long stopId) {
        try {
            Route updatedRoute = routeService.removeStopFromRoute(routeId, stopId);
            return ResponseEntity.ok(updatedRoute);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Log the exception or handle it according to your requirements
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{routeId}/bus-details")
    public ResponseEntity<List<RouteScheduleDTO>> getRouteSchedules(@PathVariable Long routeId) {
        List<RouteScheduleDTO> routeSchedules = routeService.getRouteSchedules(routeId);
        return ResponseEntity.ok(routeSchedules);
    }
}
