package com.han.service.utils;

import com.han.dto.PostCreateDto;
import com.han.dto.PostDetailDto;
import com.han.dto.PostListDto;
import com.han.dto.PostUpdateDto;
import com.han.exception.PostException;
import com.han.model.Company;
import com.han.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  public Post toPost(PostUpdateDto dto) {
    if (dto == null || !dto.isValid()) {
      log.error("Error in toPost: >> Invalid PostUpdateDto");
      throw new PostException("Invalid PostUpdateDto");
    }

    Company companyProxy = entityManager.getReference(Company.class, dto.getCompanyId());

    Post post = new Post();
    post.setId(dto.getId());
    post.setCompany(companyProxy);
    post.setPosition(dto.getPosition());
    post.setCompensation(dto.getCompensation());
    post.setJobDescription(dto.getJobDescription());
    post.setTechStack(dto.getTechStack());

    return post;
  }

  public PostUpdateDto toDto(Post post) {
    if (post == null) {
      log.error("Error in toPostUpdateDto: >> Invalid Post");
      throw new PostException("Invalid Post");
    }

    Integer companyId = post.getCompany().getId();
    PostUpdateDto dto = new PostUpdateDto();

    dto.setId(post.getId());
    dto.setCompanyId(companyId);
    dto.setPosition(post.getPosition());
    dto.setCompensation(post.getCompensation());
    dto.setJobDescription(post.getJobDescription());
    dto.setTechStack(post.getTechStack());

    return dto;
  }

  public Pageable toPostPageable(PostListDto dto) {
    Integer offset = dto.getPage() - 1;
    Integer limit = dto.getLimit();
    String sort = dto.getSort();
    String order = dto.getOrder();

    return order.equals("desc")
            ? PageRequest.of(offset, limit, Sort.by(sort).descending())
            : PageRequest.of(offset, limit, Sort.by(sort).ascending());
  }

  public PostDetailDto toPostDetailDto(Post post) {
    Company company = post.getCompany();
    PostDetailDto dto = new PostDetailDto();

    dto.setId(post.getId());
    dto.setCompanyName(company.getName());
    dto.setCountry(company.getCountry());
    dto.setCity(company.getCity());
    dto.setCompensation(post.getCompensation());
    dto.setPosition(post.getPosition());
    dto.setJobDescription(post.getJobDescription());
    dto.setTechStack(post.getTechStack());

    return dto;
  }
}
