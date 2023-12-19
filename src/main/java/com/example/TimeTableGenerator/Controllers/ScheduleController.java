package com.example.TimeTableGenerator.Controllers;
import com.example.TimeTableGenerator.DTOs.ScheduleDTO;
import com.example.TimeTableGenerator.Entities.Schedule;
import com.example.TimeTableGenerator.Repositories.ScheduleRepository;
import com.example.TimeTableGenerator.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Controller
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleRepository scheduleRepository, ScheduleService scheduleService) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String displaySchedule(){
        return "schedule";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute ScheduleDTO event){
        Schedule newEvent = new Schedule();
        newEvent.setCourseCode(event.getCourseCode());
        newEvent.setLocation(event.getLocation());
        newEvent.setLecturer(event.getLecturer());
        newEvent.setDayOfWeek(event.getDayOfWeek());
        newEvent.setEndTime(event.getStartTime());
        newEvent.setStartTime(event.getStartTime());
        scheduleRepository.save(newEvent);
        return "generate";
    }

    @GetMapping("/timetable")
    public String displayTimeTable(Model model){
        List<Schedule> schedules = scheduleService.getAllSchedule();
        schedules.sort(Comparator.comparing(Schedule::getDayOfWeek, new DayOfWeekComparator()));
        model.addAttribute("schedules",schedules);
        return "test";
    }
    @GetMapping("/newSchedule")
    public String newSchedule(){
        scheduleRepository.deleteAll();
        return "redirect:generate";
    }
}
