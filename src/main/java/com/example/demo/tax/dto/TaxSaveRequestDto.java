package com.example.demo.tax.dto;

import com.example.demo.prd.enums.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
public class TaxSaveRequestDto {

    @Enumerated(EnumType.STRING)
    private Category category;
    private int taxrate;
}
