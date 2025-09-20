package com.aurionpro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.app.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
