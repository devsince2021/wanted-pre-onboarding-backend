package com.han.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private Company company;

  @NotNull
  @Column(name = "job_position")
  private String position;

  @Column(name = "compensation")
  private Integer compensation;

  @NotNull
  @Column(name = "job_description")
  private String jobDescription;

  @NotNull
  @Column(name = "tech_stack")
  private String techStack;

  public Post(Company company, String position, Integer compensation, String jobDescription, String techStack) {
    this.company = company;
    this.position = position;
    this.compensation = compensation;
    this.jobDescription = jobDescription;
    this.techStack = techStack;
  }
}
