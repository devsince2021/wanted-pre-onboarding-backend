package com.han.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PostListResDto {
  private Integer id;

  private String companyName;
  private String country;
  private String city;

  private String position;
  private Integer compensation;
  private String techStack;

  public PostListResDto(Integer id, String companyName, String country, String city, String position, Integer compensation, String techStack) {
    this.id = id;
    this.companyName = companyName;
    this.country = country;
    this.city = city;
    this.position = position;
    this.compensation = compensation;
    this.techStack = techStack;
  }
}