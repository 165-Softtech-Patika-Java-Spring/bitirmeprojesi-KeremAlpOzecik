package com.example.demo.mgr.dao;

import com.example.demo.mgr.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao extends JpaRepository<Manager,Long> {
    Manager findByUsername(String username);
    Manager findByEmail(String email);
    Manager findByPhone(String phone);



}
