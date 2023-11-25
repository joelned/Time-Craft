package com.example.TimeTableGenerator.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Integer departmentId;
    String departmentName;
    String headOfDepartment;
    @OneToMany
    List<Lecturer> lecturers;


}
