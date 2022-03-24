package com.example.demo.mgr.service;


import com.example.demo.gen.exceptions.ItemFoundException;
import com.example.demo.gen.exceptions.ItemNotFoundException;
import com.example.demo.mgr.conveter.ManagerMapper;
import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.mgr.entity.Manager;
import com.example.demo.mgr.enums.ManagerErrorMessage;
import com.example.demo.mgr.enums.ManagerExistError;
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
        if(!isManagerExistByUsername(managerSaveRequestDto)){

            Manager manager = ManagerMapper.INSTANCE.SaveManagerDtoToManager(managerSaveRequestDto);
            String password = passwordEncoder.encode(manager.getPassword());
            manager.setPassword(password);

            manager = managerEntityService.save(manager);

            ManagerDto managerDto = ManagerMapper.INSTANCE.ManagerToManagerDto(manager);

            return managerDto;
        }
        else throw new ItemFoundException(ManagerExistError.MANAGER_EXIST_ERROR);

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
        String password = passwordEncoder.encode(manager.getPassword());
        manager.setPassword(password);
        manager = managerEntityService.save(manager);

        ManagerDto managerDto = ManagerMapper.INSTANCE.ManagerToManagerDto(manager);

        return managerDto;
    }

    private boolean isManagerExistByUsername(ManagerSaveRequestDto managerSaveRequestDto){
        if (managerEntityService.findByUsername(managerSaveRequestDto.getUsername())==null){
            return false;
        }
        else
            return true;
    }
    private void controlIsManagerExist(ManagerUpdateRequestDto managerUpdateRequestDto) {

        Long id = managerUpdateRequestDto.getId();
        boolean isExist = managerEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(ManagerErrorMessage.MANAGER_ERROR_MESSAGE);
        }
    }

}
