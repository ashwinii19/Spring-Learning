package com.aurionpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.entity.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long>{

}
