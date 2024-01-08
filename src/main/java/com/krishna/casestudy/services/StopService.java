package com.krishna.casestudy.services;

import com.krishna.casestudy.models.Stop;

import java.util.List;
import java.util.Optional;

public interface StopService {
    List<Stop> getAllStops();
    Optional<Stop> getStopById(Long id);
    Stop createStop(Stop stop);
    Stop updateStop(Long id, Stop updatedStop);
    void deleteStop(Long id);
}
