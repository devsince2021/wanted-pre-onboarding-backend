package com.han.unit.controller;

import com.han.controller.CompanyController;
import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.exception.CompanyException;
import com.han.model.Company;
import com.han.service.CompanyService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
  class GetCompanyDetailTest {
    private Integer matchedId = 1;
    private Integer unmatchedId = 7;
    private Company dummyCompany = new Company();

    @Test
    public void getCompanyDetail_Throws_RuntimeException_When_Service_Throws_RuntimeException() {
      when(companyService.getCompanyDetail(matchedId)).thenThrow(RuntimeException.class);
      assertThrows(RuntimeException.class, () -> companyController.getCompanyDetail(matchedId));
    }

    @Test
    public void getCompanyDetail_Throws_CompanyException_When_Service_Throws_CompanyException() {
      when(companyService.getCompanyDetail(matchedId)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyController.getCompanyDetail(matchedId));
    }

    @Test
    public void getCompanyDetail_Throws_CompanyException_When_Service_Returns_Null() {
      when(companyService.getCompanyDetail(unmatchedId)).thenReturn(null);
      assertThrows(CompanyException.class, () -> companyController.getCompanyDetail(unmatchedId));
    }

    @Test
    public void getCompanyDetail_Returns_Company_When_Service_Returns_Company() {
      when(companyService.getCompanyDetail(matchedId)).thenReturn(dummyCompany);
      Company company = companyController.getCompanyDetail(matchedId);

      assertThat(company).isEqualTo(dummyCompany);
    }
  }

  @Nested
  class UpdateCompanyTest {
    private CompanyUpdateDto dummyDto = new CompanyUpdateDto();
    private Company dummyCompany = new Company();

    @Test
    public void updateCompany_Throws_RuntimeException_When_Service_Throws() {
      when(companyService.updateCompany(dummyDto)).thenThrow(RuntimeException.class);
      assertThrows(RuntimeException.class, () -> companyController.updateCompany(dummyDto));
    }

    @Test
    public void updateCompany_Throws_CompanyException_When_Service_Throws() {
      when(companyService.updateCompany(dummyDto)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyController.updateCompany(dummyDto));
    }

    @Test
    public void updateCompany_Throws_CompanyException_When_Service_Return_Null() {
      when(companyService.updateCompany(dummyDto)).thenReturn(null);
      assertThrows(CompanyException.class, () -> companyController.updateCompany(dummyDto));
    }

    @Test
    public void updateCompany_Returns_Company_When_Service_Returns_Company() {
      when(companyService.updateCompany(dummyDto)).thenReturn(dummyCompany);
      Company company = companyController.updateCompany(dummyDto);

      assertThat(company).isEqualTo(dummyCompany);
    }
  }


  @Nested
  class CreateCompanyTest {

    private CompanyCreateDto dummyDto = new CompanyCreateDto();

    private Company dummyCompany = new Company();

    @Test
    public void createCompany_Throws_RuntimeException_When_Service_Throws() {
      when(companyService.createCompany(dummyDto)).thenThrow(RuntimeException.class);
      assertThrows(RuntimeException.class, () -> companyController.createCompany(dummyDto));
    }

    @Test
    public void createCompany_Throws_CompanyException_When_Service_Throws() {
      when(companyService.createCompany(dummyDto)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyController.createCompany(dummyDto));
    }

    @Test
    public void createCompany_Throws_CompanyException_When_Service_Return_Null() {
      when(companyService.createCompany(dummyDto)).thenReturn(null);
      assertThrows(CompanyException.class, () -> companyController.createCompany(dummyDto));
    }

    @Test
    public void createCompany_Return_True_When_Service_Return_Company() {
      when(companyService.createCompany(dummyDto)).thenReturn(dummyCompany);
      Boolean isSuccess = companyController.createCompany(dummyDto);
      assertThat(isSuccess).isTrue();
    }
  }

  @Nested
  class HandleCompanyExceptionTest {
    private CompanyException dummyException = new CompanyException("Test Exception");

    @Test
    public void handleCompanyException_Returns_ExceptionMessage() {
      String message = companyController.handleCompanyException(dummyException);
      assertThat(message).isEqualTo(dummyException.getMessage());
    }
  }
}
