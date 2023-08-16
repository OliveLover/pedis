package com.example.footcare.Model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "USERS")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String password;

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
