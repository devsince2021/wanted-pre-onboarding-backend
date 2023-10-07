package com.han.service.utils;

import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.model.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompanyFormatter {
  public Company toCompany(CompanyCreateDto dto) {
    if (dto == null || !dto.isValid()) {
      log.error("Error in toCompany: >> Invalid CompanyCreateDto");
      throw new IllegalArgumentException("Invalid CompanyCreateDto");
    }

    Company company = new Company();
    company.setName(dto.getCompanyName());
    company.setCountry(dto.getCountry());
    company.setCity(dto.getCity());

    return company;
  }

  public Company toCompany(CompanyUpdateDto dto) {
    if (dto == null || !dto.isValid() ) {
      log.error("Error in toCompany: >> Invalid CompanyUpdateDto");
      throw new IllegalArgumentException("Invalid CompanyUpdateDto");
    }

    Company company = new Company();
    company.setId(dto.getId());
    company.setName(dto.getCompanyName());
    company.setCountry(dto.getCountry());
    company.setCity(dto.getCity());

    return company;
  }


}
