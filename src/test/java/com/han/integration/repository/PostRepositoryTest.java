package com.han.integration.repository;

import com.han.exception.PostException;
import com.han.model.Company;
import com.han.model.Post;
import com.han.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@DataJpaTest
public class PostRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private PostRepository postRepository;

  @Nested
  class UpdateTest {

    private Company savedCompany;
    private Post savedPost;

    @BeforeEach
    public void setUp() {
      savedCompany = new Company("wanted1", "Korea", "Seoul");
      testEntityManager.persist(savedCompany);

      savedPost = new Post(savedCompany, "Be", 1000, "apweojfawe", "spring");
      testEntityManager.persist(savedPost);
    }

    @Test
    public void update_Throws_PostException_When_Post_Does_Not_Exist() {
      Post notExistingPost = new Post(10, savedCompany, "Be", 1000, "apweojfawe", "spring");
      assertThrows(PostException.class, () -> postRepository.update(notExistingPost));
    }

    @Test
    public void update_Returns_updated_Post_When_Success() {
      Post targetPost = new Post(
              savedPost.getId(),
              savedPost.getCompany(),
              "BackEnd",
              1000,
              "this is edited",
              "JS"
      );

      Post updatedPost = postRepository.update(targetPost);
      assertThat(updatedPost.getPosition()).isEqualTo(targetPost.getPosition());
    }
  }

  @Nested
  class SaveTest {
    Company dummyCompany = new Company(1, "wanted1", "Korea", "Seoul");
    Post dummyPost = new Post(dummyCompany, "Backend", 1000, "blahblah", "spring");

    @Test
    public void save_Throws_InvalidDataAccessApiUsageException_When_Entity_Is_Null() {
      Post invalidPost = null;
      assertThrows(InvalidDataAccessApiUsageException.class, () -> postRepository.save(invalidPost));
    }

    @Test
    public void save_Return_Saved_Post() {
      Post post = postRepository.save(dummyPost);

      assertThat(post.getId()).isNotNull();
      assertThat(post.getPosition()).isEqualTo(dummyPost.getPosition());
    }
  }
}
