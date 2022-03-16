package com.example.demo.mgr.conveter;

import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.mgr.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);
    Manager SaveManagerDtoToManager(ManagerSaveRequestDto managerSaveRequestDto);
    Manager UpdateManagerDtoToManager(ManagerUpdateRequestDto managerUpdateRequestDto);
    ManagerDto ManagerToManagerDto(Manager manager);
    List<ManagerDto> ManagerListToManagerDtoList(List<Manager> managerList);

}
