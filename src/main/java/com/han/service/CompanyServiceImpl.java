package com.han.service;

import com.han.dto.CompanyCreateDto;
import com.han.model.Company;
import com.han.repository.CompanyRepository;
import com.han.service.utils.CompanyFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired
  private CompanyFormatter companyFormatter;
  @Autowired
  private CompanyRepository companyRepository;

  @Override
  public Company createCompany(CompanyCreateDto dto) {
    Company company = companyFormatter.toCompany(dto);
    Company savedCompany = companyRepository.save(company);

    return savedCompany;
  }
}
