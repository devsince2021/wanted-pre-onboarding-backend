package com.han.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
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

  @ToString.Exclude
  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
  private List<Post> posts;

  public Company(String name, String country, String city) {
    this.name = name;
    this.country = country;
    this.city = city;
  }

  public Company(Integer id, String name, String country, String city) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.city = city;
  }
}

