package com.han.repository;

import com.han.dto.PostListResDto;
import com.han.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {

  @Query("SELECT new com.han.dto.PostListResDto(" +
            "p.id, p.company.name, p.company.country, p.company.city," +
            "p.position, p.compensation, p.techStack" +
          ") FROM Post p JOIN p.company")
  List<PostListResDto> findAllWithCompany(Pageable pageable);
}
