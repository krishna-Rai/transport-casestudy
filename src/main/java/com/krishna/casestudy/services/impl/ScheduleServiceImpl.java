package com.krishna.casestudy.services.impl;

import com.krishna.casestudy.exceptions.ScheduleOverlapException;
import com.krishna.casestudy.models.Schedule;
import com.krishna.casestudy.repositories.ScheduleRepository;
import com.krishna.casestudy.services.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    public boolean isScheduleOverlap(Schedule newSchedule) {
        List<Schedule> existingSchedules = scheduleRepository.findByBusAndRouteAndDepartureTimeBetweenOrArrivalTimeBetween(
                newSchedule.getBus(),
                newSchedule.getRoute(),
                newSchedule.getDepartureTime(),
                newSchedule.getArrivalTime(),
                newSchedule.getDepartureTime(),
                newSchedule.getArrivalTime()
        );

        return !existingSchedules.isEmpty();
    }
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule createSchedule(Schedule schedule) {
        // You can perform additional validation or logic before saving
        if(isScheduleOverlap(schedule)){
            throw new ScheduleOverlapException("Schedule overlaps with existing schedules for the same bus on the same route.");
        }
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        if(isScheduleOverlap(updatedSchedule)){
            throw new ScheduleOverlapException("Schedule overlaps with existing schedules for the same bus on the same route.");
        }
        if (scheduleRepository.existsById(id)) {
            updatedSchedule.setId(id);
            return scheduleRepository.save(updatedSchedule);
        } else {
            throw new EntityNotFoundException("Schedule with id " + id + " not found.");
        }
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
