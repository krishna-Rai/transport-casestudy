package com.krishna.casestudy.services.impl;

import com.krishna.casestudy.models.Stop;
import com.krishna.casestudy.repositories.StopRepository;
import com.krishna.casestudy.services.StopService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopServiceImpl implements StopService {

    @Autowired
    private StopRepository stopRepository;
    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Optional<Stop> getStopById(Long id) {
        return stopRepository.findById(id);
    }

    public Stop createStop(Stop stop) {
        // You can perform additional validation or logic before saving
        return stopRepository.save(stop);
    }

    public Stop updateStop(Long id, Stop updatedStop) {
        if (stopRepository.existsById(id)) {
            updatedStop.setId(id);
            return stopRepository.save(updatedStop);
        } else {
            throw new EntityNotFoundException("Stop with id " + id + " not found.");
        }
    }

    public void deleteStop(Long id) {
        stopRepository.deleteById(id);
    }

}
