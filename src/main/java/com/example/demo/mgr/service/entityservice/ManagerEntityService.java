package com.example.demo.mgr.service.entityservice;

import com.example.demo.gen.service.BaseEntityService;
import com.example.demo.mgr.dao.ManagerDao;
import com.example.demo.mgr.entity.Manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ManagerEntityService extends BaseEntityService<Manager, ManagerDao> {

    public ManagerEntityService(ManagerDao dao) {
        super(dao);
    }
    public Manager findByUsername(String username){
        return getDao().findByUsername(username);
    }

}
