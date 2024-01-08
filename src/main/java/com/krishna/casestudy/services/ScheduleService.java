package com.krishna.casestudy.services;

import com.krishna.casestudy.models.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    Optional<Schedule> getScheduleById(Long id);
    Schedule createSchedule(Schedule schedule);
    Schedule updateSchedule(Long id, Schedule updatedSchedule);
    void deleteSchedule(Long id);
    public boolean isScheduleOverlap(Schedule newSchedule);
}
