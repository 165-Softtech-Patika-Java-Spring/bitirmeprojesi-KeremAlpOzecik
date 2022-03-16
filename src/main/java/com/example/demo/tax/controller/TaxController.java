package com.example.demo.tax.controller;

import com.example.demo.gen.dto.RestResponse;
import com.example.demo.prd.enums.Category;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import com.example.demo.tax.service.TaxService;
import com.example.demo.tax.entitiy.Tax;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/taxes")
@RequiredArgsConstructor
public class TaxController {
    private final TaxService taxService;

    @PostMapping
    public ResponseEntity save(@RequestBody TaxSaveRequestDto taxSaveRequestDto){
        TaxDto taxDto = taxService.save(taxSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(taxDto)) ;
    }

    @GetMapping("/{category}")
    public ResponseEntity findByCategory(@PathVariable Category category){
        TaxDto byCategory = taxService.findByCategory(category);
        return ResponseEntity.ok(RestResponse.of(byCategory));
    }
    @PatchMapping
    public ResponseEntity updateTaxRate(@RequestParam Category category,@RequestParam int taxrate){
        TaxDto taxDto = taxService.updateTaxRate(taxrate, category);
        return ResponseEntity.ok(RestResponse.of(taxDto));
    }
}
