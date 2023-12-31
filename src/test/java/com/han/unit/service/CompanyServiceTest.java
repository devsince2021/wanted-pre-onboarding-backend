package com.han.unit.service;

import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.exception.CompanyException;
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

import java.util.Optional;

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
  class GetCompanyDetailTest {
    private Integer matchedId = 1;
    private Integer unmatchedId = 6;

    private Company dummyCompany = new Company(1, "wanted4-1", "UK", "London");


    @Test
    public void getCompanyDetail_Throws_InvalidDataAccessApiUsageException_When_Id_Is_Null() {
      when(companyRepository.findById(null)).thenThrow(InvalidDataAccessApiUsageException.class);
      assertThrows(InvalidDataAccessApiUsageException.class, () -> companyService.getCompanyDetail(null));
    }

    @Test
    public void getCompanyDetail_Throws_CompanyException_When_Id_Is_Not_Matched() {
      when(companyRepository.findById(unmatchedId)).thenReturn(Optional.empty());
      assertThrows(CompanyException.class, () -> companyService.getCompanyDetail(unmatchedId));
    }

    @Test
    public void getCompanyDetail_Returns_When_Id_Is_Matched() {
      when(companyRepository.findById(matchedId)).thenReturn(Optional.of(dummyCompany));
      Company company = companyService.getCompanyDetail(matchedId);

      assertThat(company).isEqualTo(dummyCompany);
    }
  }

  @Nested
  class UpdateCompanyTest {
    CompanyUpdateDto dummyDto = new CompanyUpdateDto(1, "wanted4-1", "UK", "London");
    Company dummyCompany = new Company(1, "wanted4-1", "UK", "London");

    @Test
    public void updateCompany_Throws_InvalidDataAccessApiUsageException_When_Save_Throws_InvalidDataAccessApiUsageException() {
      when(companyFormatter.toCompany(dummyDto)).thenReturn(null);
      when(companyRepository.save(null)).thenThrow(InvalidDataAccessApiUsageException.class);
      assertThrows(InvalidDataAccessApiUsageException.class, () -> companyService.updateCompany(dummyDto));
    }

    @Test
    public void updateCompany_Throws_CompanyException_When_Dto_Is_Null() {
      CompanyUpdateDto invalidDto = null;
      when(companyFormatter.toCompany(invalidDto)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyService.updateCompany(invalidDto));
    }

    @Test
    public void updateCompany_Throws_CompanyException_When_Dto_Is_Invalid() {
      CompanyUpdateDto invalidDto = new CompanyUpdateDto();
      when(companyFormatter.toCompany(invalidDto)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyService.updateCompany(invalidDto));
    }

    @Test
    public void updateCompany_Throws_CompanyException_When_Repository_Return_Null() {
      when(companyFormatter.toCompany(dummyDto)).thenReturn(dummyCompany);
      when(companyRepository.save(dummyCompany)).thenReturn(null);
      assertThrows(CompanyException.class, () -> companyService.updateCompany(dummyDto));
    }

    @Test
    public void updateCompany_Returns_Updated_Company() {
      when(companyFormatter.toCompany(dummyDto)).thenReturn(dummyCompany);
      when(companyRepository.save(dummyCompany)).thenReturn(dummyCompany);
      Company company = companyService.updateCompany(dummyDto);

      assertThat(company.getId()).isNotNull();
      assertThat(company.getName()).isEqualTo(dummyCompany.getName());
    }
  }

  @Nested
  class CreateCompanyTest {
    private CompanyCreateDto dummyDto = new CompanyCreateDto("wanted4", "Korea", "Seoul");
    private Company dummyCompany = new Company("wanted4", "Korea", "Seoul");

    @Test
    public void createCompany_Throws_InvalidDataAccessApiUsageException_When_Save_Throws() {
      when(companyFormatter.toCompany(dummyDto)).thenReturn(null);
      when(companyRepository.save(null)).thenThrow(InvalidDataAccessApiUsageException.class);
      assertThrows(InvalidDataAccessApiUsageException.class, () -> companyService.createCompany(dummyDto));
    }

    @Test
    public void createCompany_Throws_CompanyException_When_CompanyCreate_Is_Null() {
      CompanyCreateDto invalidDto = null;
      when(companyFormatter.toCompany(invalidDto)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyService.createCompany(invalidDto));
    }

    @Test
    public void createCompany_Throws_CompanyException_When_CompanyCreate_Is_Invalid() {
      CompanyCreateDto invalidDto = new CompanyCreateDto(null, null, null);
      when(companyFormatter.toCompany(invalidDto)).thenThrow(CompanyException.class);
      assertThrows(CompanyException.class, () -> companyService.createCompany(invalidDto));
    }

    @Test
    public void createCompany_Throws_CompanyException_When_Repository_Return_Null() {
      when(companyFormatter.toCompany(dummyDto)).thenReturn(dummyCompany);
      when(companyRepository.save(dummyCompany)).thenReturn(null);
      assertThrows(CompanyException.class, () -> companyService.createCompany(dummyDto));

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
