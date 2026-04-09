package com.courseportal.coursebackend.dto;

public class LessonDTO {

    private Long lessonId;
    private String title;
    private String videoUrl;
    private boolean completed;

    public LessonDTO(Long lessonId, String title, String videoUrl, boolean completed) {
        this.lessonId = lessonId;
        this.title = title;
        this.videoUrl = videoUrl;
        this.completed = completed;
    }

    public Long getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public String getVideoUrl() { return videoUrl; }
    public boolean isCompleted() { return completed; }
}