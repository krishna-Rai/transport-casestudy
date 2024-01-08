package com.krishna.casestudy.repositories;

import com.krishna.casestudy.models.Bus;
import com.krishna.casestudy.models.Route;
import com.krishna.casestudy.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findByBusAndRouteAndDepartureTimeBetweenOrArrivalTimeBetween(
            Bus bus,
            Route route,
            LocalDateTime departureStartTime,
            LocalDateTime departureEndTime,
            LocalDateTime arrivalStartTime,
            LocalDateTime arrivalEndTime
    );
}
