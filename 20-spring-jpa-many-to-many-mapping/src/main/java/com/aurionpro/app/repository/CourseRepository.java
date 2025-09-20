package com.aurionpro.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.app.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByCode(String code);
}
