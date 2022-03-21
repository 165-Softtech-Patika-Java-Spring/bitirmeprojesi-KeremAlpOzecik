package com.example.demo.mgr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManagerSaveRequestDto {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String password;
}
