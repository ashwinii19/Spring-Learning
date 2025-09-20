package com.aurionpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.entity.Role;
import com.aurionpro.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role>findByRolename(String role);
}
