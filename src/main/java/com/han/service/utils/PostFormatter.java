package com.han.service.utils;

import com.han.dto.PostCreateDto;
import com.han.exception.PostException;
import com.han.model.Company;
import com.han.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostFormatter {

  @PersistenceContext
  private EntityManager entityManager;
  public Post toPost(PostCreateDto dto) {
    if (dto == null || !dto.isValid()) {
      log.error("Error in toPost: >> Invalid PostCreateDto");
      throw new PostException("Invalid PostCreateDto");
    }

    Company companyProxy = entityManager.getReference(Company.class, dto.getCompanyId());

    Post post = new Post();
    post.setCompany(companyProxy);
    post.setPosition(dto.getPosition());
    post.setCompensation(dto.getCompensation());
    post.setJobDescription(dto.getJobDescription());
    post.setTechStack(dto.getTechStack());

    return post;
  }
}
