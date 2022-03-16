package com.example.demo.mgr.service;


import com.example.demo.gen.exceptions.ItemNotFoundException;
import com.example.demo.mgr.conveter.ManagerMapper;
import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.mgr.entity.Manager;
import com.example.demo.mgr.enums.ManagerErrorMessage;
import com.example.demo.mgr.service.entityservice.ManagerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerEntityService managerEntityService;
    private final PasswordEncoder passwordEncoder;

    public List<ManagerDto> findAll() {

        List<Manager> managerList = managerEntityService.findAll();

        List<ManagerDto> managerDtoList = ManagerMapper.INSTANCE.ManagerListToManagerDtoList(managerList);

        return managerDtoList;
    }

    public ManagerDto save(ManagerSaveRequestDto managerSaveRequestDto) {

        Manager manager = ManagerMapper.INSTANCE.SaveManagerDtoToManager(managerSaveRequestDto);

        String password = passwordEncoder.encode(manager.getPassword());
        manager.setPassword(password);

        manager = managerEntityService.save(manager);

        ManagerDto managerDto = ManagerMapper.INSTANCE.ManagerToManagerDto(manager);

        return managerDto;
    }


    public void deleteManager(Long id) {

        Manager manager = managerEntityService.getByIdWithControl(id);

        managerEntityService.delete(manager);
    }

    public ManagerDto findById(Long id) {

        Manager manager = managerEntityService.getByIdWithControl(id);

        ManagerDto managerDto = ManagerMapper.INSTANCE.ManagerToManagerDto(manager);

        return managerDto;
    }


    public ManagerDto update(ManagerUpdateRequestDto managerUpdateRequestDto) {

        controlIsManagerExist(managerUpdateRequestDto);

        Manager manager = ManagerMapper.INSTANCE.UpdateManagerDtoToManager(managerUpdateRequestDto);
        manager = managerEntityService.save(manager);

        ManagerDto managerDto = ManagerMapper.INSTANCE.ManagerToManagerDto(manager);

        return managerDto;
    }

    private void controlIsManagerExist(ManagerUpdateRequestDto managerUpdateRequestDto) {

        Long id = managerUpdateRequestDto.getId();
        boolean isExist = managerEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(ManagerErrorMessage.MANAGER_ERROR_MESSAGE);
        }
    }

}
