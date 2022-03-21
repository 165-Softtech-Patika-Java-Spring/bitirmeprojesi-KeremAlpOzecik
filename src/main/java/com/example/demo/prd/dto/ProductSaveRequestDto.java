package com.example.demo.prd.dto;

import com.example.demo.prd.enums.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@Builder
public class ProductSaveRequestDto {

    private String productName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private double noTaxPrice;
    private double taxPrice; //hesaplama yapılacak
    private double lastPrice; // hesaplama yapılacak
}
