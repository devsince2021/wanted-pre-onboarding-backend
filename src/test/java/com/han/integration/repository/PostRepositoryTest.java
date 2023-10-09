package com.han.integration.repository;

import com.han.model.Company;
import com.han.model.Post;
import com.han.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
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
  TestEntityManager testEntityManager;

  @Autowired
  private PostRepository postRepository;

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
