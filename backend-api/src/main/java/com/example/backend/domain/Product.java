package com.example.backend.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
// @IdClass(ProductId.class)

public class Product implements Serializable {
    @EmbeddedId
    private ProductId id;

    public Product() {
        this.id = new ProductId();
    }

    public Product(String userid, Integer proteinid) {
        this.id = new ProductId(userid, proteinid);
    }

    public String getUserid() {
        return this.id.getUserid();
    }

    // useridのsetter
    public void setUserid(String userid) {
        this.id.setUserid(userid);
    }

    // proteinidのgetter
    public Integer getProteinid() {
        return this.id.getProteinid();
    }

    // proteinidのsetter
    public void setProteinid(Integer proteinid) {
        this.id.setProteinid(proteinid);
    }
}