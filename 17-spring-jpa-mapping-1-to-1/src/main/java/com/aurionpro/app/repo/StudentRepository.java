package com.aurionpro.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.app.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}
