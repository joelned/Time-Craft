package com.example.TimeTableGenerator.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table
public class Schedule {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Integer scheduleId;
    String dayOfWeek;
    String courseCode;
    String location;
    LocalTime startTime;
    LocalTime endTime;
    String lecturer;

}
