package com.han.controller;

import com.han.constants.EndPoint;
import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.exception.CompanyException;
import com.han.model.Company;
import com.han.service.CompanyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @PostMapping(EndPoint.COMPANY)
  @ResponseStatus(HttpStatus.CREATED)
  public Boolean createCompany(@RequestBody @Valid CompanyCreateDto dto) {
    Company company = companyService.createCompany(dto);

    if (company == null) {
      throw new CompanyException("Fail to create company");
    }

    return true;
  }

  @PutMapping(EndPoint.COMPANY)
  @ResponseStatus(HttpStatus.OK)
  public Company updateCompany(@Valid @RequestBody CompanyUpdateDto dto) {
    Company company = companyService.updateCompany(dto);

    if (company == null) {
      throw new CompanyException("Fail to update company");
    }

    return company;
  }

  @GetMapping(EndPoint.COMPANY_DETAIL)
  public ResponseEntity<Company> getCompanyDetail(@PathVariable(EndPoint.COMPANY_ID) Integer id) {
    Company company = companyService.getCompanyDetail(id);
    return new ResponseEntity<>(company, HttpStatus.OK);
  }

  @ExceptionHandler(CompanyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleCompanyException(CompanyException ex) {
    log.error("Handle CompanyException: >> " + ex.getMessage());
    return ex.getMessage();
  }

}
