package com.example.backend.repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.example.backend.domain.Testes;
public class TestRepositorySpecifications {
    public static Specification<Testes> hasFlavorCodeIn(List<Integer> flavorCodes) {
        return new Specification<Testes>() {
            @Override
            public Predicate toPredicate(Root<Testes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (flavorCodes.isEmpty()) {
                    return null;
                }
                return root.get("flavorcode").in(flavorCodes);
            }
        };
    }

    public static Specification<Testes> hasPurposeCodeIn(List<Integer> purposeCodes) {
        return new Specification<Testes>() {
            @Override
            public Predicate toPredicate(Root<Testes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (purposeCodes.isEmpty()) {
                    return null;
                }
                return root.get("purposecode").in(purposeCodes);
            }
        };
    }

    public static Specification<Testes> hasComponentCodeIn(List<Integer> componentCodes) {
        return new Specification<Testes>() {
            @Override
            public Predicate toPredicate(Root<Testes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (componentCodes.isEmpty()) {
                    return null;
                }
                return root.get("componentcode").in(componentCodes);
            }
        };
    }

    public static Specification<Testes> hasPriceCodeIn(List<Integer> priceCodes) {
        return new Specification<Testes>() {
            @Override
            public Predicate toPredicate(Root<Testes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (priceCodes.isEmpty()) {
                    return null;
                }
                return root.get("pricecode").in(priceCodes);
            }
        };
    }
    
}