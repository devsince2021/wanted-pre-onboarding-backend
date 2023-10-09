package com.han.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateDto {
  @NotBlank(message = "Company name should not be blank or empty")
  private String companyName;
  @NotBlank(message = "Country should not be blank or empty")
  private String country;
  @NotBlank(message = "City should not be blank or empty")
  private String city;

  public boolean isValid() {
    return companyName != null && !companyName.trim().isEmpty()
            && country != null && !country.trim().isEmpty()
            && city != null && !city.trim().isEmpty();
  }
}
