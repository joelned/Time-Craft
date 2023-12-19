package com.example.TimeTableGenerator.DTOs;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleDTO {
    String dayOfWeek;
    String courseCode;
    String location;
    LocalTime startTime;
    LocalTime endTime;
    String lecturer;
}
