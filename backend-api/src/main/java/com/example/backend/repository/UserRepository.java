package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.backend.domain.Users;

public interface UserRepository extends JpaRepository<Users, Integer>,JpaSpecificationExecutor<Users>{
    
}