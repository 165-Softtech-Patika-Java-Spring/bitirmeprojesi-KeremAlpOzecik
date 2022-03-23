package com.example.demo.tax.service;

import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.ProductService;
import com.example.demo.prd.service.entityservice.ProductEntityService;
import com.example.demo.tax.dao.TaxDao;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import com.example.demo.tax.entitiy.Tax;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class TaxServiceTest {

    @Mock
    private ProductEntityService productEntityService;

    @Mock
    private TaxDao taxDao;


    @InjectMocks
    private TaxService taxService;

    @Test
    void findByCategory() {
            Tax tax = mock(Tax.class);
            ArrayList<Tax> taxArrayList =new ArrayList<>();
            taxArrayList.add(tax);
            taxDao.findByCategory(Category.FOOD);
            taxService.findByCategory(Category.FOOD);
            assertEquals(1,taxArrayList.size());
    }

    @Test
    void save() {
        TaxSaveRequestDto taxSaveRequestDto=mock(TaxSaveRequestDto.class);
        when(taxSaveRequestDto.getCategory()).thenReturn(Category.FOOD);
        Tax tax=mock(Tax.class);
        when(taxDao.save(any())).thenReturn(tax);
        TaxDto result = taxService.save(taxSaveRequestDto);
        assertEquals(Category.FOOD, result.getCategory());
    }

    @Test
    void updateTaxRate() {
        Tax tax= mock(Tax.class);
        when(taxDao.findByCategory(Category.FOOD)).thenReturn(tax);
        taxService.updateTaxRate(10,Category.FOOD);
        when(tax.getTaxrate()).thenReturn(10);
        assertEquals(10,tax.getTaxrate());

    }
}