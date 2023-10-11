package com.han.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDetailDto {
  private Integer id;
  private String companyName;
  private String country;
  private String city;
  private String position;
  private Integer compensation;
  private String techStack;
  private String jobDescription;

  public PostDetailDto(Integer id, String companyName, String country, String city, String position, Integer compensation, String techStack, String jobDescription) {
    this.id = id;
    this.companyName = companyName;
    this.country = country;
    this.city = city;
    this.position = position;
    this.compensation = compensation;
    this.techStack = techStack;
    this.jobDescription = jobDescription;
  }
}
