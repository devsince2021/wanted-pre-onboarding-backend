package com.han.service;

import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.exception.CompanyException;
import com.han.model.Company;
import com.han.repository.CompanyRepository;
import com.han.service.utils.CompanyFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

    if (savedCompany == null) {
      log.error("Fail create company: >> " + dto);
      throw new CompanyException("Fail to create company");
    }

    return savedCompany;
  }

  @Override
  public Company updateCompany(CompanyUpdateDto dto) {
    Company company = companyFormatter.toCompany(dto);
    Company updatedCompany = companyRepository.save(company);

    if (updatedCompany == null) {
      log.error("Fail update company: >> " + dto);
      throw new CompanyException("Fail to update company");
    }

    return updatedCompany;
  }

  @Override
  public Company getCompanyDetail(Integer id) {
    Optional<Company> maybeCompany = companyRepository.findById(id);
    return maybeCompany.orElseThrow(() -> new RuntimeException("Fail to find companyDetail"));
  }

}
