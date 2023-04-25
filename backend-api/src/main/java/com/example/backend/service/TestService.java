package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.backend.domain.Product;
import com.example.backend.domain.Testes;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.TestRepository;
import com.example.backend.repository.TestRepositorySpecifications;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ProductRepository productRepository;

    public Testes getTestes(Integer id){
        Optional<Testes> testesOptional =testRepository.findById(id);
        if(testesOptional.isPresent()){
            return testesOptional.get();
        }
        return null;
    }  
    
    public List<Testes> findTestes(List<Integer> flavorCodes, List<Integer> purposeCodes,List<Integer> componentCodes,List<Integer> priceCodes) {
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

    public Boolean addToFavorites(Integer Userid, Integer Proteinid) {
        Product vv = new Product();
        vv.setUserid(Userid);
        vv.setProteinid(Proteinid);
        productRepository.save(vv);
        return true;
    }

    public Boolean addToFavorites2(Integer Userid, Integer Proteinid) {
        productRepository.deleteById(productRepository.findByUseridAndProteinid(Userid,Proteinid).getFavoriteid());
        return true;
    }

}

