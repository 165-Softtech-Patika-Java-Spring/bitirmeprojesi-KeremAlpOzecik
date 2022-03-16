package com.example.demo.sec.service;

import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.mgr.entity.Manager;
import com.example.demo.mgr.service.ManagerService;
import com.example.demo.mgr.service.entityservice.ManagerEntityService;
import com.example.demo.sec.dto.LoginRequestDto;
import com.example.demo.sec.enums.EnumJwtConstant;
import com.example.demo.sec.security.JwtTokenGenerator;
import com.example.demo.sec.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ManagerService managerService;
    private final ManagerEntityService managerEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public ManagerDto register(ManagerSaveRequestDto managerSaveRequestDto) {

        ManagerDto managerDto = managerService.save(managerSaveRequestDto);

        return managerDto;
    }

    public String login(LoginRequestDto loginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    public Manager getCurrentManager() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Manager manager = null;
        if (jwtUserDetails != null){
            manager = managerEntityService.getByIdWithControl(jwtUserDetails.getId());
        }

        return manager;
    }

    public Long getCurrentCustomerId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
