package com.han.unit.service.utils;

import com.han.dto.CompanyCreateDto;
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
  class ToCompany_Test {
    CompanyCreateDto dummyDto = new CompanyCreateDto("wanted4", "Korea", "Seoul");
    Company dummyCompany = new Company("wanted4", "Korea", "Seoul");

    @Test
    public void toCompany_Throws_IllegalArgumentException_When_CompanyCreateDto_Is_Invalid() {
      CompanyCreateDto invalidDto = new CompanyCreateDto();
      assertThrows(IllegalArgumentException.class, () -> companyFormatter.toCompany(invalidDto));
    }

    @Test
    public void toCompany_Throws_IllegalArgumentException_When_CompanyCreateDto_Is_Null() {
      assertThrows(IllegalArgumentException.class, () -> companyFormatter.toCompany(null));
    }

    @Test
    public void toCompany_Return_Company_When_CompanyCreateDto_Is_Valid() {
      Company company = companyFormatter.toCompany(dummyDto);
      assertThat(company).isEqualTo(dummyCompany);
    }
  }
}
