package com.courseportal.coursebackend.repository;



import com.courseportal.coursebackend.model.Module;
import com.courseportal.coursebackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByCourse(Course course);
}