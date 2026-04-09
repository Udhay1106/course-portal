package com.courseportal.coursebackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "modules")
@Setter
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Course course;



    // getters & setters
}