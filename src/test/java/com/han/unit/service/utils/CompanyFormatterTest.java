package com.han.unit.service.utils;

import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.model.Company;
import com.han.service.utils.CompanyFormatter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class CompanyFormatterTest {
  private CompanyFormatter companyFormatter = new CompanyFormatter();

  @Nested
  class ToCompany_With_CompanyUpdateDto_Test {
    CompanyUpdateDto dummyDto = new CompanyUpdateDto(1,"wanted4-1", "UK", "London");
    Company dummyCompany = new Company(1,"wanted4-1", "UK", "London");

    @Test
    public void toCompany_Throws_IllegalArgumentException_When_CompanyUpdateDto_Is_Invalid() {
      CompanyUpdateDto invalidDto = new CompanyUpdateDto();
      assertThrows(IllegalArgumentException.class, () -> companyFormatter.toCompany(invalidDto));
    }

    @Test
    public void toCompany_Throws_IllegalArgumentException_When_CompanyUpdateDto_Is_Null() {
      CompanyUpdateDto invalidDto = null;
      assertThrows(IllegalArgumentException.class, () -> companyFormatter.toCompany(invalidDto));
    }

    @Test
    public void toCompany_Return_Company_When_CompanyUpdateDto_Is_Valid() {
      Company company = companyFormatter.toCompany(dummyDto);
      assertThat(company).isEqualTo(dummyCompany);
    }
  }

  @Nested
  class ToCompany_With_CompanyCreateDto_Test {
    CompanyCreateDto dummyDto = new CompanyCreateDto("wanted4", "Korea", "Seoul");
    Company dummyCompany = new Company("wanted4", "Korea", "Seoul");

    @Test
    public void toCompany_Throws_IllegalArgumentException_When_CompanyCreateDto_Is_Invalid() {
      CompanyCreateDto invalidDto = new CompanyCreateDto();
      assertThrows(IllegalArgumentException.class, () -> companyFormatter.toCompany(invalidDto));
    }

    @Test
    public void toCompany_Throws_IllegalArgumentException_When_CompanyCreateDto_Is_Null() {
      CompanyCreateDto invalidDto = null;
      assertThrows(IllegalArgumentException.class, () -> companyFormatter.toCompany(invalidDto));
    }

    @Test
    public void toCompany_Return_Company_When_CompanyCreateDto_Is_Valid() {
      Company company = companyFormatter.toCompany(dummyDto);
      assertThat(company).isEqualTo(dummyCompany);
    }
  }
}
