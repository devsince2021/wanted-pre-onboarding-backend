package com.han.unit.controller;

import com.han.controller.CompanyController;
import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.model.Company;
import com.han.service.CompanyService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {
  @Mock
  private CompanyService companyService;
  @InjectMocks
  private CompanyController companyController;

  @Nested
  class UpdateCompany_Test {
    private CompanyUpdateDto dummyDto = new CompanyUpdateDto();
    private Company dummyCompany = new Company();

    @Test
    public void updateCompany_Throws_RuntimeException_When_Service_Throws_RuntimeException() {
      when(companyService.updateCompany(dummyDto)).thenThrow(RuntimeException.class);
      assertThrows(RuntimeException.class, () -> companyController.updateCompany(dummyDto));
    }
    @Test
    public void updateCompany_Returns_Company_With_STATUS_OK_When_Service_Returns_Company() {
      when(companyService.updateCompany(dummyDto)).thenReturn(dummyCompany);
      ResponseEntity<Company> company = companyController.updateCompany(dummyDto);

      assertThat(company).isInstanceOf(ResponseEntity.class);
      assertThat(company.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(company.getBody()).isEqualTo(dummyCompany);
    }
  }


  @Nested
  class CreateNewCompany_Test {

    private CompanyCreateDto dummyDto = new CompanyCreateDto();

    private Company dummyCompany = new Company();

    @Test
    public void createNewCompany_Throws_IllegalArgumentException() {
      when(companyService.createCompany(dummyDto)).thenThrow(IllegalArgumentException.class);
      assertThrows(IllegalArgumentException.class, () -> companyController.createNewCompany(dummyDto));
    }

    @Test
    public void createNewCompany_Return_False_When_Service_Return_Null() {
      when(companyService.createCompany(dummyDto)).thenReturn(null);
      ResponseEntity<Boolean> result = companyController.createNewCompany(dummyDto);
      assertThat(result.getBody()).isFalse();
    }

    @Test
    public void createNewCompany_Return_True_When_Service_Return_Company() {
      when(companyService.createCompany(dummyDto)).thenReturn(dummyCompany);
      ResponseEntity<Boolean> result = companyController.createNewCompany(dummyDto);
      assertThat(result.getBody()).isTrue();
    }
  }
}
