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

    @Column(name = "mail")
    private String mail;

    @Column(name = "homenumber")
    private String homenumber;

    @Column(name = "homeaddress")
    private String homeaddress;


    @Override
    protected Object keyObject() {
        return getId();
    }
    
}