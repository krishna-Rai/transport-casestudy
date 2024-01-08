package com.krishna.casestudy.services.impl;

import com.krishna.casestudy.models.Bus;
import com.krishna.casestudy.repositories.BusRepository;
import com.krishna.casestudy.services.BusService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.UniqueConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    BusRepository busRepository;
    @Override
    public Bus addNewBus(Bus bus) throws Exception {
        if(busRepository.findByBusNumber(bus.getBusNumber()) == null) {
            return busRepository.save(bus);
        }else {
            throw new Exception("Bus Number already exists");
        }

    }
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    public Optional<Bus> getBusByNumber(String busNumber) {
        return busRepository.findByBusNumber(busNumber);
    }

    public Bus createBus(Bus bus) {
        // You can perform additional validation or logic before saving
        return busRepository.save(bus);
    }

    public Bus updateBus(Long id, Bus updatedBus) {
        if (busRepository.existsById(id)) {
            updatedBus.setId(id);
            return busRepository.save(updatedBus);
        } else {
            // Handle not found scenario
            throw new EntityNotFoundException("Bus with id " + id + " not found.");
        }
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

}
