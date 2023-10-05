package com.han.service;

import com.han.dto.CompanyCreateDto;
import com.han.model.Company;

public interface CompanyService {
  Company createCompany(CompanyCreateDto dto);
}
