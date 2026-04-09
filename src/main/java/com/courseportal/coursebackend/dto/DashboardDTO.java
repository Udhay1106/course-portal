package com.courseportal.coursebackend.dto;

public class DashboardDTO {

    private Long courseId;
    private String title;
    private int progress;

    public DashboardDTO(Long courseId, String title, int progress) {
        this.courseId = courseId;
        this.title = title;
        this.progress = progress;
    }

    public Long getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public int getProgress() { return progress; }
}