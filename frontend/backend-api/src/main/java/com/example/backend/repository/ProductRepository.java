package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.domain.Product;
import com.example.backend.domain.ProductId;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
    Product findById_UseridAndId_Proteinid(String userId, Integer proteinId);

    @Query("SELECT p.id.proteinid FROM Product p WHERE p.id.userid = :userId")
    List<Integer> findByUserId(@Param("userId") String userId);
}