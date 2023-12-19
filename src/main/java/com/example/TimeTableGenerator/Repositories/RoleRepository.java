package com.example.TimeTableGenerator.Repositories;


import com.example.TimeTableGenerator.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
