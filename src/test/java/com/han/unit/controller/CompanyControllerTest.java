package com.han.unit.controller;

import com.han.controller.CompanyController;
import com.han.dto.CompanyCreateDto;
import com.han.model.Company;
import com.han.service.CompanyService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;

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
