package com.example.demo.tax.controller;

import com.example.demo.gen.dto.RestResponse;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.enums.Category;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import com.example.demo.tax.service.TaxService;
import com.example.demo.tax.entitiy.Tax;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/taxes")
@RequiredArgsConstructor
public class TaxController {
    private final TaxService taxService;
    @Operation(tags = "Tax Controller", description = "Categories: FOOD, STATIONERY, TEXTILE, TECHNOLOGY, CLEANING, OTHERS", summary = "Save TAX")
    @PostMapping
    public ResponseEntity save(@RequestBody TaxSaveRequestDto taxSaveRequestDto){
        TaxDto taxDto = taxService.save(taxSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(taxDto)) ;
    }
    @Operation(tags = "Tax Controller", description = "Get Tax by Category", summary = "Get TAX")
    @GetMapping("/categories")
    public ResponseEntity findByCategory(@RequestParam("category") Category category){
        TaxDto taxDto = taxService.findByCategory(category);
        return ResponseEntity.ok(RestResponse.of(taxDto));
    }
    @Operation(tags = "Tax Controller", description = "Update Category Taxrate", summary = "Update Category Taxrate")
    @PatchMapping
    public ResponseEntity updateTaxRate(@RequestParam("category") Category category,@RequestParam int taxrate){
        TaxDto taxDto = taxService.updateTaxRate(taxrate, category);
        return ResponseEntity.ok(RestResponse.of(taxDto));
    }
}
