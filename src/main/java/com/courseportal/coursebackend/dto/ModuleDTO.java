package com.courseportal.coursebackend.dto;



import java.util.List;

public class ModuleDTO {

    private Long moduleId;
    private String moduleTitle;
    private List<LessonDTO> lessons;

    public ModuleDTO(Long moduleId, String moduleTitle, List<LessonDTO> lessons) {
        this.moduleId = moduleId;
        this.moduleTitle = moduleTitle;
        this.lessons = lessons;
    }

    public Long getModuleId() { return moduleId; }
    public String getModuleTitle() { return moduleTitle; }
    public List<LessonDTO> getLessons() { return lessons; }
}