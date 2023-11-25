package com.example.TimeTableGenerator.Repositories;

import com.example.TimeTableGenerator.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findUsersByEmail(String email);
    User findUserByEmail(String email);
}
