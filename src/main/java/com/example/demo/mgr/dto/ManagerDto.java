package com.example.demo.mgr.dto;

import lombok.Data;

@Data
public class ManagerDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String password;
}
