package com.han.repository;

import com.han.dto.PostDetailDto;
import com.han.dto.PostListResDto;
import com.han.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {

  @Query("SELECT new com.han.dto.PostListResDto(" +
            "p.id, p.company.name, p.company.country, p.company.city," +
            "p.position, p.compensation, p.techStack" +
          ") FROM Post p JOIN p.company")
  List<PostListResDto> findAllWithCompany(Pageable pageable);

  @Query("SELECT p FROM Post p JOIN FETCH p.company WHERE p.id = :id")
  Optional<Post> findByIdWithCompany(@Param("id") Integer id);
}
