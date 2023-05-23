package com.example.backend.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.domain.Product;
import com.example.backend.domain.ProductId;
import com.example.backend.domain.Testes;
import com.example.backend.domain.Users;
import com.example.backend.repository.DetailRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.TestRepository;
import com.example.backend.repository.TestRepositorySpecifications;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DetailRepository DetailRepository;

    public Testes getTestes(Integer id) {
        Optional<Testes> testesOptional = testRepository.findById(id);
        if (testesOptional.isPresent()) {
            return testesOptional.get();
        }
        return null;
    }

    public List<Testes> findTestes(List<Integer> flavorCodes, List<Integer> purposeCodes, List<Integer> componentCodes,
            List<Integer> priceCodes) {
        Specification<Testes> specification = Specification.where(null);

        if (flavorCodes != null && !flavorCodes.isEmpty()) {
            specification = specification.and(TestRepositorySpecifications.hasFlavorCodeIn(flavorCodes));
        }

        if (purposeCodes != null && !purposeCodes.isEmpty()) {
            specification = specification.and(TestRepositorySpecifications.hasPurposeCodeIn(purposeCodes));
        }

        if (componentCodes != null && !componentCodes.isEmpty()) {
            specification = specification.and(TestRepositorySpecifications.hasComponentCodeIn(componentCodes));
        }

        if (priceCodes != null && !priceCodes.isEmpty()) {
            specification = specification.and(TestRepositorySpecifications.hasPriceCodeIn(priceCodes));
        }

        return testRepository.findAll(specification);
    }

    public List<Testes> findAllEntities() {
        return testRepository.findAllRandom();
    }

    public Boolean addToFavorites(ProductId id) {
        Product vv = new Product();
        vv.setId(id);
        productRepository.save(vv);
        return true;
    }

    public Boolean deleteFavorites(ProductId id) {
        System.out.println("サービスもへじ" + id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return true; // 削除成功
        } else {
            return false; // 削除失敗
        }
    }

    public List<Integer> getFavoritesId(String id) {
        List<Integer> puropuro = productRepository.findByUserId(id);
        System.out.println("天ぷら天ぷら天ぷら" + id);
        return puropuro;

    }

    public List<Testes> getFavorites(List<Integer> id) {
        List<Testes> testes = DetailRepository.findByIdIn(id);
        return testes;

    }

    public List<Testes> getDetail(Integer id) {
        Optional<Testes> testesOptional = DetailRepository.findById(id);
        System.out.println("寿司寿司寿司" + id);
        return testesOptional.map(Collections::singletonList).orElse(Collections.emptyList());

    }

    // ランキング用
    public List<Testes> findRanking() {
        return testRepository.findAllRanking();
    }

    public List<Testes> womans() {
        return testRepository.findAllwoman();
    }

    public List<Testes> mans() {
        return testRepository.findAllman();
    }

    public List<Testes> dogswan() {
        return testRepository.findAlldoc();
    }

    public List<Testes> getfav(Integer flavorCode,Integer id) {
      
        System.out.println("あははハハハハハははは" + flavorCode);
        return testRepository.findByFlavorCode(flavorCode,id);

    }

    
}