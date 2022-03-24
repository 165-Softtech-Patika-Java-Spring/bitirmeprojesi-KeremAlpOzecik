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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductEntityService productEntityService;
    private final TaxService taxService;

    public ProductDto save(ProductSaveRequestDto productSaveRequestDto) {
        if (Objects.isNull(productSaveRequestDto.getCategory()))
            throw new NullPointerException("Category is not valid please check and try again");
        if(productSaveRequestDto.getNoTaxPrice()<=0)
            throw new IllegalArgumentException("Price column cannot be zero or minus");
        Product product = ProductMapper.INSTANCE.SaveProductDtoToProduct(productSaveRequestDto);
        int taxrate = taxService.findByCategory(productSaveRequestDto.getCategory()).getTaxrate();

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
        double lastprice= ((taxrate/100.0)+1)*product.getNoTaxPrice();
        double taxprice=(taxrate/100.0)*product.getNoTaxPrice();
        product.setLastPrice(lastprice);
        product.setTaxPrice(taxprice);
    }

    public List<ProductDto> findByCategory(Category category){
        if (Objects.isNull(category))
            throw new NullPointerException("Category is not valid please check and try again");
        List<Product> productList = productEntityService.findByProductCategory(category);
        List<ProductDto> productDtoList = ProductMapper.INSTANCE.ProductListToProductDtoList(productList);


        return productDtoList;
    }


    public List<ProductDto> findAll(){
        List<Product> productList = productEntityService.findAll();
        List<ProductDto> productDtoList = ProductMapper.INSTANCE.ProductListToProductDtoList(productList);
        return productDtoList;

    }
    public List<ProductDto> findAllByPriceRange(double value1,double value2){
        List<Product> productList = productEntityService.findByPriceRange(value1,value2);
        List<ProductDto> productDtoList = ProductMapper.INSTANCE.ProductListToProductDtoList(productList);
        return productDtoList;

    }
    public ProductDto updateProductPrice(Long id,double price){
        if(!(price<=0)){

            Product product = productEntityService.getByIdWithControl(id);
            product.setNoTaxPrice(price);
            setPriceColumns(taxService.findByCategory(product.getCategory()).getTaxrate(),product);
            productEntityService.save(product);
            ProductDto productDto=ProductMapper.INSTANCE.ProductToProductDto(product);
            return productDto;
        }

        else throw new IllegalArgumentException("Price cannot be 0 or minus");
    }

    public ProductDto update(ProductUpdateRequestDto productUpdateRequestDto) {
        if (Objects.isNull(productUpdateRequestDto.getCategory()))
            throw new NullPointerException("Category is not valid please check and try again");
        if(productUpdateRequestDto.getNoTaxPrice()<=0)
            throw new IllegalArgumentException("Price column cannot be zero or minus");

        controlIsProductExist(productUpdateRequestDto);
        Product product = ProductMapper.INSTANCE.UpdateProductDtoToProduct(productUpdateRequestDto);
        int taxrate = taxService.findByCategory(productUpdateRequestDto.getCategory()).getTaxrate();

        setPriceColumns(taxrate,product);
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
