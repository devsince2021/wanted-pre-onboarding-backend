package com.han.service;

import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.model.Company;

public interface CompanyService {
  Company createCompany(CompanyCreateDto dto);

  Company updateCompany(CompanyUpdateDto dto);

  Company getCompanyDetail(Integer id);
}
