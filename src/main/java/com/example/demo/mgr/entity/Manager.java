package com.example.demo.mgr.entity;

import com.example.demo.gen.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Manager")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Manager extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;




}
