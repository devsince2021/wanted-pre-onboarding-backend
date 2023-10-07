package com.han.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateDto {
  @NotNull
  private Integer id;
  @NotBlank
  private String companyName;
  @NotBlank
  private String country;
  @NotBlank
  private String city;

  public boolean isValid() {
    return companyName != null && !companyName.trim().isEmpty()
            && country != null && !country.trim().isEmpty()
            && city != null && !city.trim().isEmpty()
            && id != null && id > 0;
  }
}
