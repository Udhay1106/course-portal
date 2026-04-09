package com.courseportal.coursebackend.dto;

public class LessonRequest {

    private String title;
    private String videoUrl;
    private Long moduleId;

    public String getTitle() { return title; }
    public String getVideoUrl() { return videoUrl; }
    public Long getModuleId() { return moduleId; }

    public void setTitle(String title) { this.title = title; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }
}