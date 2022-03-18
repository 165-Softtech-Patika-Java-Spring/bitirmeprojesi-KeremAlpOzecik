package com.example.demo.prd.service.entityservice;

import com.example.demo.gen.service.BaseEntityService;
import com.example.demo.prd.dao.ProductDao;
import com.example.demo.prd.dto.ProductDto;
import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductEntityService extends BaseEntityService<Product, ProductDao> {
    public ProductEntityService(ProductDao dao) {
        super(dao);
    }
    public List<Product> findByProductCategory(Category category){
     return getDao().findAllByCategory(category);
    }
    public int min(Category category){return getDao().min(category);}
    public int max(Category category){return getDao().max(category);}
    public double avg(Category category){return getDao().avg(category);}

}
