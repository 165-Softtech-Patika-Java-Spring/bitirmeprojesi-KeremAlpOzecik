package com.example.demo.sec.controller;


import com.example.demo.gen.dto.RestResponse;
import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.sec.dto.LoginRequestDto;
import com.example.demo.sec.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        String token = authenticationService.login(loginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ManagerSaveRequestDto managerSaveRequestDto){

        ManagerDto managerDto =authenticationService.register(managerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }
}
