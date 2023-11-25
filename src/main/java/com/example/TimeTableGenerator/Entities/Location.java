package com.example.TimeTableGenerator.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Location {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Integer locationId;
    String locationName;
    String faculty;
}
