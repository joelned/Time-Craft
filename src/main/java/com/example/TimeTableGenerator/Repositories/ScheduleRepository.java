package com.example.TimeTableGenerator.Repositories;

import com.example.TimeTableGenerator.Entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByDayOfWeek(String dayOfWeek);
}
