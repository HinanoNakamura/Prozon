package com.example.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.domain.Testes;

public interface TestRepository extends JpaRepository<Testes, Integer>,JpaSpecificationExecutor<Testes> {
    @Query(value = "SELECT * FROM protain ORDER BY RAND() LIMIT 1", nativeQuery=true)
    List<Testes> findAllRandom();

}