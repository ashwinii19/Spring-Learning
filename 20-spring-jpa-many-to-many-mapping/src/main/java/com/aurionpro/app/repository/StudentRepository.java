package com.aurionpro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.app.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
