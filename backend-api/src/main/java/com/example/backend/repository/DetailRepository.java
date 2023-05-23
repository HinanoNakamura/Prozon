package com.example.backend.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.domain.Testes;
public interface DetailRepository extends JpaRepository<Testes,Integer> {
    List<Testes> findByIdIn(List<Integer> id);
}