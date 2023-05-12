package com.mirea.practice18.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "roles")
@Entity
@Data
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;
}