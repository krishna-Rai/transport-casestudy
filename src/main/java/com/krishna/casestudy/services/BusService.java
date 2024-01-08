package com.krishna.casestudy.services;

import com.krishna.casestudy.models.Bus;

import java.util.List;
import java.util.Optional;

public interface BusService {
    public Bus addNewBus(Bus bus) throws Exception;
    public List<Bus> getAllBuses();
    public Optional<Bus> getBusById(Long id);
    public Optional<Bus> getBusByNumber(String busNumber);
    public Bus createBus(Bus bus);
    public Bus updateBus(Long id, Bus updatedBus);
    public void deleteBus(Long id);

}
