package com.courseportal.coursebackend.dto;


public class ModuleRequest {

    private String title;
    private Long courseId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}