package com.example.demo.prd.service;

import com.example.demo.mgr.conveter.ManagerMapper;
import com.example.demo.prd.converter.ProductMapper;
import com.example.demo.prd.dto.ProductDetailDto;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.entityservice.ProductEntityService;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import com.example.demo.tax.entitiy.Tax;
import com.example.demo.tax.service.TaxService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductEntityService productEntityService;

    @Mock
    private TaxService taxService;

    @InjectMocks
    private ProductService productService;



    @Test
    void save() {
        ProductSaveRequestDto productSaveRequestDto = mock(ProductSaveRequestDto.class);
        when(productSaveRequestDto.getCategory()).thenReturn(Category.FOOD);
        Product product = mock(Product.class);
        Tax tax= new Tax(1L,Category.FOOD,10);
        when(taxService.findByCategory(tax.getCategory())).thenReturn(new TaxDto());
        when(product.getId()).thenReturn(23L);
        when(productEntityService.save(any())).thenReturn(product);
        ProductDto result = productService.save(productSaveRequestDto);
        assertEquals(23L, result.getId());
    }

    @Test
    void testGetInfo() {
        Tax tax= new Tax(1L,Category.FOOD,10);
        when(taxService.findByCategory(tax.getCategory())).thenReturn(new TaxDto());
        when(productEntityService.min(any())).thenReturn(10);
        when(productEntityService.max(any())).thenReturn(10);
        when(productEntityService.avg(any())).thenReturn(10.0);
        ProductDetailDto detailDto = productService.getInfo(Category.FOOD);
        assertEquals(10,detailDto.getMin());
        assertEquals(10,detailDto.getMax());
        assertEquals(10.0,detailDto.getAvg());


    }

    @Test
    void setPriceColumns() {
        Product product=mock(Product.class);
        productService.setPriceColumns(10,product);
    }

    @Test
    void findByCategory() {

    }

    @Test
    void findAll() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        ProductDto productDto = mock(ProductDto.class);
        List<ProductDto>  productDtoList = new ArrayList<>();
        productDtoList.add(productDto);

        when(productEntityService.findAll()).thenReturn(productList);


        List<ProductDto> result = productService.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void updateProductPrice() {


    }

    @Test
    void update() {
        Long id = 18L;

        ProductUpdateRequestDto updateRequestDto = mock(ProductUpdateRequestDto.class);
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(id);


        boolean isExist = true;

        when(productEntityService.existsById(anyLong())).thenReturn(isExist);
        when(productEntityService.save(any())).thenReturn(product);
        when(updateRequestDto.getCategory()).thenReturn(Category.FOOD);
        Tax tax= new Tax(1L,Category.FOOD,10);
        when(taxService.findByCategory(tax.getCategory())).thenReturn(new TaxDto());

        ProductDto productDto = productService.update(updateRequestDto);

        assertEquals(id, productDto.getId());

        verify(productEntityService).existsById(anyLong());
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void findById() {
    }
}