package com.example.TimeTableGenerator.Repositories;

import com.example.TimeTableGenerator.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findUserByUsername(String username);
}
