package com.courseportal.coursebackend.service;


import com.courseportal.coursebackend.dto.LessonDTO;
import com.courseportal.coursebackend.dto.LessonRequest;
import com.courseportal.coursebackend.dto.ModuleDTO;
import com.courseportal.coursebackend.dto.ModuleRequest;
import com.courseportal.coursebackend.model.*;
import com.courseportal.coursebackend.model.Module;
import com.courseportal.coursebackend.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class LessonService {

    private final LessonRepository lessonRepo;
    private final LessonProgressRepository progressRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final ModuleRepository moduleRepo;


    public LessonService(LessonRepository lessonRepo,
                         LessonProgressRepository progressRepo,
                         UserRepository userRepo,
                         CourseRepository courseRepo,
                         EnrollmentRepository enrollmentRepo, ModuleRepository moduleRepo) {
        this.lessonRepo = lessonRepo;
        this.progressRepo = progressRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.moduleRepo = moduleRepo;
    }

    // ✅ 1. Mark lesson as completed
    public void completeLesson(String email, Long lessonId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // 🔐 Check if user enrolled in course
        Course course = lesson.getModule().getCourse();

        boolean enrolled = enrollmentRepo
                .findByUserAndCourse(user, course)
                .isPresent();

        if (!enrolled) {
            throw new RuntimeException("User not enrolled in this course");
        }

        LessonProgress progress = progressRepo
                .findByUserAndLesson(user, lesson)
                .orElse(new LessonProgress());

        progress.setUser(user);
        progress.setLesson(lesson);
        progress.setCompleted(true);

        progressRepo.save(progress);
    }

    // ✅ 2. Calculate course progress
    public int calculateProgress(String email, Long courseId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        long completedLessons =
                progressRepo.countByUserAndLesson_Module_Course(user, course);

        long totalLessons =
                lessonRepo.countByModule_Course(course);

        if (totalLessons == 0) return 0;

        return (int) ((completedLessons * 100) / totalLessons);
    }

    public List<ModuleDTO> getCourseContent(String email, Long courseId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        // 🔐 Check enrollment
        boolean enrolled = enrollmentRepo
                .findByUserAndCourse(user, course)
                .isPresent();

        if (!enrolled) {
            throw new RuntimeException("Please enroll to access course content");
        }


        List<Module> modules = moduleRepo.findByCourse(course);

        return modules.stream().map(module -> {

            List<Lesson> lessons = lessonRepo.findByModule(module);

            List<LessonDTO> lessonDTOs = lessons.stream().map(lesson -> {

                boolean completed = progressRepo
                        .findByUserAndLesson(user, lesson)
                        .map(LessonProgress::isCompleted)
                        .orElse(false);

                return new LessonDTO(
                        lesson.getId(),
                        lesson.getTitle(),
                        lesson.getVideoUrl(),
                        completed
                );

            }).collect(Collectors.toList());

            return new ModuleDTO(
                    module.getId(),
                    module.getTitle(),
                    lessonDTOs
            );

        }).collect(Collectors.toList());
    }
    // Add module...
    public Module addModule(ModuleRequest request) {

        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Module module = new Module();
        module.setTitle(request.getTitle());
        module.setCourse(course);

        return moduleRepo.save(module);
    }
    //  Add Lesson..
    public Lesson addLesson(LessonRequest request) {

        Module module = moduleRepo.findById(request.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        Lesson lesson = new Lesson();
        lesson.setTitle(request.getTitle());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setModule(module);

        return lessonRepo.save(lesson);
    }
}

