package com.han.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyCreateDto {
  @NotBlank
  private String companyName;
  @NotBlank
  private String country;
  @NotBlank
  private String city;

  public CompanyCreateDto(String companyName, String country, String city) {
    this.companyName = companyName;
    this.country = country;
    this.city = city;
  }

  public boolean isValid() {
    return companyName != null && !companyName.trim().isEmpty()
            && country != null && !country.trim().isEmpty()
            && city != null && !city.trim().isEmpty();
  }
}
