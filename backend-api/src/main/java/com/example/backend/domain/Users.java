package com.example.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data

public class Users extends GenericDomain{

    @Id
    private String id;
    
    @Column(name = "password")
    private String password;

    @Column(name = "icon")
    private String icon;

    @Override
    protected Object keyObject() {
        return getId();
    }
    
}