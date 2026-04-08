package com.courseportal.coursebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column(nullable = false)
    private String instructorName;
    @Column
    private String videoLink;

}

//course title
//description
//instructor name
//video link
//progress percentage
