package com.han.unit.service;

import com.han.dto.CompanyCreateDto;
import com.han.model.Company;
import com.han.repository.CompanyRepository;
import com.han.service.CompanyServiceImpl;
import com.han.service.utils.CompanyFormatter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
  @Mock
  private CompanyFormatter companyFormatter;
  @Mock
  private CompanyRepository companyRepository;
  @InjectMocks
  private CompanyServiceImpl companyService;

  @Nested
  class CreateCompany_Test {
    CompanyCreateDto dummyDto = new CompanyCreateDto("wanted4", "Korea", "Seoul");
    Company dummyCompany = new Company("wanted4", "Korea", "Seoul");

    @Test
    public void createCompany_Throws_InvalidDataAccessApiUsageException_When_Save_Throws() {
      when(companyFormatter.toCompany(dummyDto)).thenReturn(null);
      when(companyRepository.save(null)).thenThrow(InvalidDataAccessApiUsageException.class);
      assertThrows(InvalidDataAccessApiUsageException.class, () -> companyService.createCompany(dummyDto));
    }

    @Test
    public void createCompany_Throws_IllegalArgumentException_When_CompanyCreate_Is_Null() {
      CompanyCreateDto invalidDto = null;
      when(companyFormatter.toCompany(invalidDto)).thenThrow(IllegalArgumentException.class);
      assertThrows(IllegalArgumentException.class, () -> companyService.createCompany(invalidDto));
    }

    @Test
    public void createCompany_Throws_IllegalArgumentException_When_CompanyCreate_Is_Invalid() {
      CompanyCreateDto invalidDto = new CompanyCreateDto(null, null, null);
      when(companyFormatter.toCompany(invalidDto)).thenThrow(IllegalArgumentException.class);
      assertThrows(IllegalArgumentException.class, () -> companyService.createCompany(invalidDto));
    }

    @Test
    public void createCompany_Return_Company() {
      Company savedCompany = new Company(1, dummyCompany.getName(), dummyCompany.getCountry(), dummyCompany.getCity());

      when(companyFormatter.toCompany(dummyDto)).thenReturn(dummyCompany);
      when(companyRepository.save(dummyCompany)).thenReturn(savedCompany);
      Company company = companyService.createCompany(dummyDto);

      assertThat(company.getId()).isNotNull();
      assertThat(company.getName()).isEqualTo(dummyCompany.getName());
    }
  }
}
