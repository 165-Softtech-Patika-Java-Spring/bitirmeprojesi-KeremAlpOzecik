package com.example.demo.prd.dao;

import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {
  List<Product> findAllByCategory(Category category);

  @Query(value = "SELECT min(lastPrice) FROM Product where category=?1")
  public int min(Category cate);

  @Query(value = "SELECT max(lastPrice) FROM Product where category=?1")
  public int max(Category cate);

  @Query(value = "SELECT avg(lastPrice) FROM Product where  category=?1")
  public double avg(Category cate);


}
