package com.example.demo.tax.converter;

import com.example.demo.tax.entitiy.Tax;
import com.example.demo.tax.dto.TaxDto;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaxMapper {
    TaxMapper INSTANCE = Mappers.getMapper(TaxMapper.class);
    Tax TaxSaveRequestDtoToTax(TaxSaveRequestDto taxSaveRequestDto);
    TaxDto TaxToTaxDto(Tax tax);
    List<TaxDto> TaxListToTaxDtoList(List<Tax> taxList);

}
