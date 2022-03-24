package com.example.demo.prd.controller;

import com.example.demo.gen.dto.RestResponse;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.prd.dto.ProductDetailDto;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @Operation(tags = "Product Controller", description = "Gets All Products", summary = "Gets All Products")
    @GetMapping
    public ResponseEntity findAll(){
        List<ProductDto> productDtoList = productService.findAll();
        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }
    @Operation(tags = "Product Controller", description = "Gets Product by Id", summary = "Gets Product by Id")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @Operation(tags = "Product Controller", description = "Gets Product by Category", summary = "Gets Product by Category")
    @GetMapping("/categories")
    public ResponseEntity findByCategory(@RequestParam("category") Category category){
        List<ProductDto> productDtoList = productService.findByCategory(category);
        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }
    @Operation(tags = "Product Controller", description = "Gets Products by range of price", summary = "Gets Products by range of price")
    @GetMapping("/priceRange")
    public ResponseEntity findByPriceRange(@RequestParam("value1") double value1,@RequestParam("value2") double value2){
        List<ProductDto> productDtoList = productService.findAllByPriceRange(value1,value2);
        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }
    @Operation(tags = "Product Controller", description = "Gets Products Summary info by Category", summary = "Gets Products Summary info by Category")
    @GetMapping("/categories/info")
    public ResponseEntity infoByCategory(@RequestParam Category category){
        ProductDetailDto info = productService.getInfo(category);
        return ResponseEntity.ok(RestResponse.of(info));
    }
    @Operation(
            tags = "Product Controller",
            description = "Categories: FOOD, STATIONERY, TEXTILE, TECHNOLOGY, CLEANING, OTHERS",
            summary = "creates new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProductSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new foreign product",
                                                    summary = "New Foreign Product Example",
                                                    description = "Complete request with all available fields for foreign product",
                                                    value ="{\n" +
                                                            "  \"productName\": \"Ekmek\",\n" +
                                                            "  \"category\": \"FOOD\",\n" +
                                                            "  \"noTaxPrice\": 5,\n" +
                                                            "  \"taxPrice\": 0,\n" +
                                                            "  \"lastPrice\": 0\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "new Product Example",
                                                    summary = "New Product Example2",
                                                    description = "new Product Example2",
                                                    value ="{\n" +
                                                            "  \"productName\": \"string\",\n" +
                                                            "  \"category\": \"FOOD\",\n" +
                                                            "  \"noTaxPrice\": 0,\n" +
                                                            "  \"taxPrice\": 0,\n" +
                                                            "  \"lastPrice\": 0\n" +
                                                            "}"
                                            ),

                                    }
                            ),
                    }
            )
    )

    @PostMapping
    public ResponseEntity save(@RequestBody ProductSaveRequestDto productSaveRequestDto){

        ProductDto productDto = productService.save(productSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @Operation(
            tags = "Product Controller",
            description = "Categories: FOOD, STATIONERY, TEXTILE, TECHNOLOGY, CLEANING, OTHERS",
            summary = "update product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProductUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Update product",
                                                    summary = "Update Product Example",
                                                    description = "Update Product Example",
                                                    value ="{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"productName\": \"update\",\n" +
                                                            "  \"category\": \"FOOD\",\n" +
                                                            "  \"noTaxPrice\": 0,\n" +
                                                            "  \"taxPrice\": 0,\n" +
                                                            "  \"lastPrice\": 0\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "Update product",
                                                    summary = "Update Product Example",
                                                    description = "Update Product Example",
                                                    value ="{\n" +
                                                            "  \"id\": 0,\n" +
                                                            "  \"productName\": \"string\",\n" +
                                                            "  \"category\": \"FOOD\",\n" +
                                                            "  \"noTaxPrice\": 0,\n" +
                                                            "  \"taxPrice\": 0,\n" +
                                                            "  \"lastPrice\": 0\n" +
                                                            "}"
                                            ),

                                    }
                            ),
                    }
            )
    )
    @PutMapping
    public ResponseEntity update(@RequestBody ProductUpdateRequestDto productUpdateRequestDto){
        ProductDto productDto = productService.update(productUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }

    @Operation(tags = "Product Controller", description = "Update product price", summary = "Update product price")
    @PatchMapping
    public ResponseEntity updatePrice(@RequestParam Long id,@RequestParam int price){
        ProductDto productDto = productService.updateProductPrice(id, price);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @Operation(tags = "Product Controller", description = "Delete product by id", summary = "Delete product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
