package com.example.demo.prd.dto;

import com.example.demo.prd.enums.Category;
import lombok.Data;

@Data
public class ProductDetailDto {
   private Category category;
   private int taxrate;
   private int min;
   private int max;
   private double avg;
   private int qty;
}
