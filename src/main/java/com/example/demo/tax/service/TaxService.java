package com.example.demo.tax.service;

import com.example.demo.prd.entity.Product;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.entityservice.ProductEntityService;
import com.example.demo.tax.converter.TaxMapper;
import com.example.demo.tax.dao.TaxDao;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import com.example.demo.tax.entitiy.Tax;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaxService {
    private final TaxDao taxDao;
    private final ProductEntityService productEntityService;


    public TaxDto findByCategory(Category category){
        Tax tax = taxDao.findByCategory(category);
        TaxDto taxDto = TaxMapper.INSTANCE.TaxToTaxDto(tax);
        return taxDto;
    }

    public TaxDto save(TaxSaveRequestDto taxSaveRequestDto){
        if (Objects.isNull(taxSaveRequestDto.getCategory()))
            throw new NullPointerException("Category is not valid please check and try again");
        Tax tax = TaxMapper.INSTANCE.TaxSaveRequestDtoToTax(taxSaveRequestDto);
        taxDao.save(tax);
        TaxDto taxDto = TaxMapper.INSTANCE.TaxToTaxDto(tax);
        return taxDto;
    }
    private void setPriceColumns(double taxrate,Product product) {
        double lastprice= ((taxrate/100)+1)*product.getNoTaxPrice();
        double taxprice=(taxrate/100)*product.getNoTaxPrice();
        product.setLastPrice(lastprice);
        product.setTaxPrice(taxprice);
    }
    @Transactional
    public TaxDto updateTaxRate(int taxrate, Category category){
        Tax tax = taxDao.findByCategory(category);
        tax.setTaxrate(taxrate);
        taxDao.save(tax);
        List<Product> productList = productEntityService.findByProductCategory(category);

        for (Product product:productList
             ) {
            setPriceColumns(taxrate,product);
            productEntityService.save(product);

        }
        TaxDto taxDto = TaxMapper.INSTANCE.TaxToTaxDto(tax);

        return taxDto;

    }
}
