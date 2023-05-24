package com.example.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "protain")
@Data

public class Testes extends GenericDomain{

    @Id
    private Integer id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "flavorcode")
    private Integer flavorcode;

    @Column(name = "purposecode")
    private Integer purposecode;

    @Column(name = "componentcode")
    private Integer componentcode;

    @Column(name = "price")
    private Integer price;

    @Column(name = "pricecode")
    private Integer pricecode;

    @Column(name = "img")
    private String img;

    @Column(name = "detail")
    private String detail;

    @Override
    protected Object keyObject() {
        return getId();
    }
}
