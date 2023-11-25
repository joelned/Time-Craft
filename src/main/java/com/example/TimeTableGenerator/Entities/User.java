package com.example.TimeTableGenerator.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Entity
@Data
@Table
public class User {
    @Id
    @GeneratedValue(
             strategy = GenerationType.IDENTITY
            )
    Integer userId;
    String email;
    String password;
}
