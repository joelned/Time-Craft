package com.example.TimeTableGenerator.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Lecturer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Integer lecturerId;
    String lecturerName;

    @OneToOne
    Department department;
}
