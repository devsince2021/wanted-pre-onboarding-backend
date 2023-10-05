package com.han.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "country", nullable = false)
  private String country;

  @NotNull
  @Column(name = "city", nullable = false)
  private String city;

  public Company(String name, String country, String city) {
    this.name = name;
    this.country = country;
    this.city = city;
  }
}

