package com.example.demo.mgr.service;

import com.example.demo.gen.exceptions.ItemNotFoundException;
import com.example.demo.mgr.conveter.ManagerMapper;
import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.mgr.entity.Manager;
import com.example.demo.mgr.enums.ManagerErrorMessage;
import com.example.demo.mgr.service.entityservice.ManagerEntityService;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.ProductService;
import com.example.demo.prd.service.entityservice.ProductEntityService;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.entitiy.Tax;
import com.example.demo.tax.service.TaxService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {
    @Mock
    private ManagerEntityService managerEntityService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ManagerService managerService;
    @Test
    void findAll() {
        Manager manager = mock(Manager.class);
        List<Manager> managerList = new ArrayList<>();
        managerList.add(manager);
        when(managerEntityService.findAll()).thenReturn(managerList);
        List<ManagerDto> result = managerService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void save() {
        ManagerSaveRequestDto managerSaveRequestDto = mock(ManagerSaveRequestDto.class);
        when(managerSaveRequestDto.getPassword()).thenReturn("123");

        Manager manager = mock(Manager.class);
        when(manager.getId()).thenReturn(1L);

        when(passwordEncoder.encode(anyString())).thenReturn("kerem");
        when(managerEntityService.save(any())).thenReturn(manager);

        ManagerDto result = managerService.save(managerSaveRequestDto);

        assertEquals(1L, result.getId());
    }
    @Test
    void shouldNotSaveWhenParameterIsNull() {

        assertThrows(NullPointerException.class, () -> managerService.save(null));

    }


    @Test
    void deleteManager() {
        Manager manager = mock(Manager.class);

        when(managerEntityService.getByIdWithControl(anyLong())).thenReturn(manager);

        managerService.deleteManager(anyLong());

        verify(managerEntityService).getByIdWithControl(anyLong());
        verify(managerEntityService).delete(any());
    }

    @Test
    void findById() {
        Long id = 18L;

        Manager manager = mock(Manager.class);
        when(manager.getId()).thenReturn(id);

        when(managerEntityService.getByIdWithControl(id)).thenReturn(manager);

        ManagerDto managerDto = managerService.findById(id);

        assertEquals(id, managerDto.getId());
    }

    @Test
    void update() {
        Long id = 18L;

        ManagerUpdateRequestDto managerUpdateRequestDto = mock(ManagerUpdateRequestDto.class);
        Manager manager = mock(Manager.class);
        when(manager.getId()).thenReturn(id);

        boolean isExist = true;

        when(managerEntityService.existsById(anyLong())).thenReturn(isExist);
        when(managerEntityService.save(any())).thenReturn(manager);

        ManagerDto managerDto = managerService.update(managerUpdateRequestDto);

        assertEquals(id, managerDto.getId());

        verify(managerEntityService).existsById(anyLong());

    }
    @Test
    void shouldNotUpdateWhenCustomerDoesNotExist() {

        ManagerUpdateRequestDto managerUpdateRequestDto = mock(ManagerUpdateRequestDto.class);

        when(managerEntityService.existsById(anyLong())).thenThrow(ItemNotFoundException.class);

        ItemNotFoundException itemNotFoundException = assertThrows(ItemNotFoundException.class, () -> managerService.update(managerUpdateRequestDto));

        verify(managerEntityService).existsById(anyLong());
    }

}