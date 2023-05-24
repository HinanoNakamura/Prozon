package com.example.backend.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ProductId implements Serializable {
    @Column(name = "Userid")
    private String userid;

    @Column(name = "Proteinid")
    private Integer proteinid;

    // その他のフィールドとメソッド

    public ProductId() {}
    
    public ProductId(String userid, Integer proteinid) {
        this.userid = userid;
        this.proteinid = proteinid;
    }
}