package com.example.demo.prd.converter;



import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ProductMapper {


    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product SaveProductDtoToProduct(ProductSaveRequestDto productSaveRequestDto);
    Product UpdateProductDtoToProduct(ProductUpdateRequestDto productUpdateRequestDto);


    ProductDto ProductToProductDto(Product product);

    List<ProductDto> ProductListToProductDtoList(List<Product> productList);

}