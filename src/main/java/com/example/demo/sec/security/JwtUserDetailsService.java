package com.example.demo.sec.security;

import com.example.demo.mgr.entity.Manager;
import com.example.demo.mgr.service.entityservice.ManagerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final ManagerEntityService managerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Manager manager = managerEntityService.findByUsername(username);

        return JwtUserDetails.create(manager);
    }

    public UserDetails loadUserByUserId(Long id) {

        Manager manager = managerEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(manager);
    }
}

