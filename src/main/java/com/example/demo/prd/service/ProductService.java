package com.example.demo.prd.service;

import com.example.demo.gen.exceptions.ItemNotFoundException;
import com.example.demo.prd.converter.ProductMapper;
import com.example.demo.prd.dto.ProductDetailDto;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.enums.ProductErrorMessage;
import com.example.demo.prd.service.entityservice.ProductEntityService;
import com.example.demo.tax.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductEntityService productEntityService;
    private final TaxService taxService;

    public ProductDto save(ProductSaveRequestDto productSaveRequestDto) {

        Product product = ProductMapper.INSTANCE.SaveProductDtoToProduct(productSaveRequestDto);
        int taxrate = taxService.findByCategory(productSaveRequestDto.getCategory()).getTaxrate();
        System.out.println("TAX RATE"+taxrate);
        setPriceColumns(taxrate,product);
        product = productEntityService.save(product);
        ProductDto productDto = ProductMapper.INSTANCE.ProductToProductDto(product);
        return productDto;
    }
    public ProductDetailDto getInfo(Category category){
        ProductDetailDto productDetailDto=new ProductDetailDto();
        productDetailDto.setCategory(category);
        productDetailDto.setTaxrate(taxService.findByCategory(category).getTaxrate());
        productDetailDto.setMin(productEntityService.min(category));
        productDetailDto.setMax(productEntityService.max(category));
        productDetailDto.setAvg(productEntityService.avg(category));
        productDetailDto.setQty(findByCategory(category).size());
        return productDetailDto;
    }

    public void setPriceColumns(double taxrate,Product product) {
        double lastprice= ((taxrate/100)+1)*product.getNoTaxPrice();
        double taxprice=(taxrate/100)*product.getNoTaxPrice();
        product.setLastPrice(lastprice);
        product.setTaxPrice(taxprice);
    }

    public List<ProductDto> findByCategory(Category category){
        List<Product> productList = productEntityService.findByProductCategory(category);
        List<ProductDto> productDtoList = ProductMapper.INSTANCE.ProductListToProductDtoList(productList);


        return productDtoList;
    }


    public List<ProductDto> findAll(){
        List<Product> productList = productEntityService.findAll();
        List<ProductDto> productDtoList = ProductMapper.INSTANCE.ProductListToProductDtoList(productList);
        return productDtoList;

    }
    public ProductDto updateProductPrice(Long id,double price){
        Product product = productEntityService.getByIdWithControl(id);
        product.setNoTaxPrice(price);
        setPriceColumns(taxService.findByCategory(product.getCategory()).getTaxrate(),product);
        productEntityService.save(product);
        ProductDto productDto=ProductMapper.INSTANCE.ProductToProductDto(product);
        return productDto;

    }

    public ProductDto update(ProductUpdateRequestDto productUpdateRequestDto) {

        controlIsProductExist(productUpdateRequestDto);

        Product product = ProductMapper.INSTANCE.UpdateProductDtoToProduct(productUpdateRequestDto);
        product = productEntityService.save(product);

        ProductDto productDto = ProductMapper.INSTANCE.ProductToProductDto(product);

        return productDto;
    }
    public void deleteProduct(Long id) {

        Product product = productEntityService.getByIdWithControl(id);

        productEntityService.delete(product);
    }

    public ProductDto findById(Long id) {

        Product product = productEntityService.getByIdWithControl(id);

        ProductDto productDto = ProductMapper.INSTANCE.ProductToProductDto(product);

        return productDto;
    }


    private void controlIsProductExist(ProductUpdateRequestDto productUpdateRequestDto) {

        Long id = productUpdateRequestDto.getId();
        boolean isExist = productEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(ProductErrorMessage.PRODUCT_ERROR_MESSAGE);
        }
    }


}
