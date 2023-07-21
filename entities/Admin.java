package com.project.hr.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String password;

}
