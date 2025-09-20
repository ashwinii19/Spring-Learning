package com.aurionpro.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.app.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {}

