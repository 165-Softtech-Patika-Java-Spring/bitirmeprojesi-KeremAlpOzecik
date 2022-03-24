package com.example.demo.prd.entity;

import com.example.demo.gen.entity.BaseEntity;
import com.example.demo.prd.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotEmpty(message = "Product name cannot be empty")
private String productName;
@Enumerated(EnumType.STRING)
private Category category;
private double noTaxPrice;
private double taxPrice; //hesaplama yapılacak
private double lastPrice; // hesaplama yapılacak

}
