package com.courseportal.coursebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;


@Entity
@Data
@Table(name = "enrollments")
@Setter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    private Course course;

    private int progress;
    private boolean completed;

//    public void setProgress(int progress) {
//    }

    // Getters & Setters
}