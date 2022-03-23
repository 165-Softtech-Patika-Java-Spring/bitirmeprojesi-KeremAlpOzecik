package com.example.demo.prd.service;

import com.example.demo.prd.dto.ProductDetailDto;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
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
    void shouldfindByCategory() {

        Product product = mock(Product.class);
        ArrayList<Product> productArrayList =new ArrayList<>();
        productArrayList.add(product);
        productService.findByCategory(Category.FOOD);
        assertEquals(1,productArrayList.size());
    }
    @Test
    void shouldfindByPriceRange() {

        Product product = mock(Product.class);
        Product product2 = mock(Product.class);
        when(product.getLastPrice()).thenReturn(11.0);
        when(product2.getLastPrice()).thenReturn(12.0);
        ArrayList<Product> productArrayList=new ArrayList<>();
        productArrayList.add(product);
        productArrayList.add(product2);

       when(productEntityService.findByPriceRange(1.0,110)).thenReturn(productArrayList);

        List<ProductDto> allByPriceRange = productService.findAllByPriceRange(1.0, 110.0);

        assertEquals(2, allByPriceRange.size());
    }
//    @Test
//    void shouldNotfindByCategory() {
//
//        Product product = mock(Product.class);
//        ArrayList<Product> productArrayList =new ArrayList<>();
//        productArrayList.add(product);
//        when(productService.findByCategory(null)).thenThrow(java.lang.NullPointerException.class);
//
//        assertThrows(NullPointerException.class,()->productService.findByCategory(any()));
//        verify(productEntityService).findByProductCategory(any());
//
//
//
//    }

    @Test
    void findAll() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.findAll()).thenReturn(productList);
        List<ProductDto> result = productService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void updateProductPrice() {

        Product product=mock(Product.class);
        when(productEntityService.getByIdWithControl(23L)).thenReturn(product);
        TaxDto taxDto =mock(TaxDto.class);
        when(taxService.findByCategory(any())).thenReturn(taxDto);
        when(taxDto.getTaxrate()).thenReturn(10);
        when(product.getNoTaxPrice()).thenReturn(100.0);
        when(product.getId()).thenReturn(23L);
        productService.updateProductPrice(23L,100.0);

        assertEquals(23L,product.getId());
        assertEquals(100.0,product.getNoTaxPrice());



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

        Product product = mock(Product.class);

        when(productEntityService.getByIdWithControl(anyLong())).thenReturn(product);

        productService.deleteProduct(anyLong());

        verify(productEntityService).getByIdWithControl(anyLong());
        verify(productEntityService).delete(any());
    }

    @Test
    void findById() {
        Long id = 18L;

        Product product = mock(Product.class);
        when(product.getId()).thenReturn(id);

        when(productEntityService.getByIdWithControl(id)).thenReturn(product);

        ProductDto productDto = productService.findById(id);

        assertEquals(id, productDto.getId());
    }
}