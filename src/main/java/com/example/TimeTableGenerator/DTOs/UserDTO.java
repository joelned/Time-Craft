package com.example.TimeTableGenerator.DTOs;

import com.example.TimeTableGenerator.Entities.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    String username;
    String password;
    String name;
}
