package com.courseportal.coursebackend.dto;

import lombok.Data;

@Data
public class CourseRequest {

    private String title;
    private String description;
    private String instructorName;



    // getters & setters
}