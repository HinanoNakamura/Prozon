package com.example.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.domain.Testes;

public interface TestRepository extends JpaRepository<Testes, Integer>,JpaSpecificationExecutor<Testes> {
    @Query(value = "SELECT * FROM protain ORDER BY RAND() LIMIT 1", nativeQuery=true)
    List<Testes> findAllRandom();

    @Query(value = "SELECT * FROM protain ORDER BY RAND() LIMIT 8", nativeQuery=true)
    List<Testes> findAllRanking();

    @Query(value = "SELECT * FROM protain  where componentcode = 2 and purposecode <> 3 ORDER BY RAND() LIMIT 8", nativeQuery=true)
    List<Testes> findAllwoman();

    @Query(value = "SELECT * FROM protain where componentcode = 1 and purposecode <> 3 ORDER BY RAND() LIMIT 8", nativeQuery=true)
    List<Testes> findAllman();

    @Query(value = "SELECT * FROM protain where purposecode = 3 ORDER BY RAND() LIMIT 5", nativeQuery=true)
    List<Testes> findAlldoc();

    @Query(value = "SELECT * FROM protain where flavorcode = :flavorCode && id != :Id  ORDER BY RAND() LIMIT 3 ", nativeQuery=true)
    List<Testes> findByFlavorCode(Integer flavorCode, Integer Id);
}