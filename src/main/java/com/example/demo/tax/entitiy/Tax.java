package com.example.demo.tax.entitiy;

import com.example.demo.prd.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
@Entity
@Table(name = "Tax")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor

public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Min(value =1, message = "tax rate cannot be minus value")
    private int taxrate;

}
