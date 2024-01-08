package com.krishna.casestudy.repositories;

import com.krishna.casestudy.models.Route;
import com.krishna.casestudy.models.dtos.RouteScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM get_route_schedules(:routeId)")
    List<RouteScheduleDTO> getRouteSchedules(@Param("routeId") Long routeId);
}
