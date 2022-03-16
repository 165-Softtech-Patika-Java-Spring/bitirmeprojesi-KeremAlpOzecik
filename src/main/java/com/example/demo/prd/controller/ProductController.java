package com.example.demo.prd.controller;

import com.example.demo.gen.dto.RestResponse;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity findAll(){
        List<ProductDto> productDtoList = productService.findAll();
        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @GetMapping("/categories")
    public ResponseEntity findByCategory(@RequestParam Category category){
        List<ProductDto> productDtoList = productService.findByCategory(category);
        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }
    @Operation(tags = "Categories: FOOD, STATIONERY, TEXTILE, TECHNOLOGY, CLEANING, OTHERS")
    @PostMapping
    public ResponseEntity save(ProductSaveRequestDto productSaveRequestDto){
        ProductDto productDto = productService.save(productSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @PutMapping
    public ResponseEntity update(ProductUpdateRequestDto productUpdateRequestDto){
        ProductDto productDto = productService.update(productUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @PatchMapping
    public ResponseEntity updatePrice(@RequestParam Long id,@RequestParam int price){
        ProductDto productDto = productService.updateProductPrice(id, price);
        return ResponseEntity.ok(RestResponse.of(productDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
