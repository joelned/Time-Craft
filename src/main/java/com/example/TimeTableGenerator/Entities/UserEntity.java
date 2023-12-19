package com.example.TimeTableGenerator.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table
public class UserEntity {
    @Id
    @GeneratedValue(
             strategy = GenerationType.IDENTITY
            )
    private Integer Id;
    private String name;
    private String username;
    private String password;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "Id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "Id")
    )
    List<Role> role = new ArrayList<>();
}
