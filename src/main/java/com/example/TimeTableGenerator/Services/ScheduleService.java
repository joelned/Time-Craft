package com.example.TimeTableGenerator.Services;

import com.example.TimeTableGenerator.Entities.Schedule;
import com.example.TimeTableGenerator.Repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedule(){
        return scheduleRepository.findAll();
    }
    public void saveSchedule(Schedule schedule){
        scheduleRepository.save(schedule);
    }

}
