package com.example.footcare.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "USERS")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String username;

    @Column
    String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
