package com.han.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Column(name = "company_id")
  private Integer companyId;

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
}
