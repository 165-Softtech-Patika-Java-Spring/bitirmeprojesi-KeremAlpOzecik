package com.example.demo.tax.dao;

import com.example.demo.prd.enums.Category;
import com.example.demo.tax.entitiy.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDao extends JpaRepository<Tax,Long> {
    Tax findByCategory(Category category);
}
