package com.project.hr.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userName;
    String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admins_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Admin admin;
}
