package com.han.unit.service.utils;

import com.han.dto.PostUpdateDto;
import com.han.exception.PostException;
import com.han.model.Company;
import com.han.model.Post;
import com.han.service.utils.PostFormatter;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PostFormatterTest {

  @Mock
  private EntityManager entityManager;

  @InjectMocks
  private PostFormatter postFormatter;

  @Nested
  class ToPostFromUpdateDtoTest {
    private PostUpdateDto dummyDto = new PostUpdateDto(1, 1, "Backend", 1000, "jd", "spring");
    private Company dummyCompany;
    private Post dummyPost;

    @BeforeEach
    public void setUp() {
      this.dummyCompany = new Company(dummyDto.getCompanyId(), "wanted1", "Korea", "Seoul");
      this.dummyPost = new Post(dummyDto.getId(), dummyCompany, dummyDto.getPosition(), dummyDto.getCompensation(), dummyDto.getJobDescription(), dummyDto.getTechStack());
    }

    @Test
    public void toPost_Throws_PostException_When_PostUpdateDto_Is_Invalid() {
      PostUpdateDto invalidDto = new PostUpdateDto();
      assertThrows(PostException.class, () -> postFormatter.toPost(invalidDto));
    }

    @Test
    public void toPost_Throws_PostException_When_PostUpdateDto_Is_Null() {
      PostUpdateDto invalidDto = null;
      assertThrows(PostException.class, () -> postFormatter.toPost(invalidDto));
    }

    @Test
    public void toPost_Returns_Post_From_PostUpdateDto() {
      Post post = postFormatter.toPost(dummyDto);
      assertThat(post.getId()).isEqualTo(dummyPost.getId());
    }
  }

  @Nested
  class ToUpdateDtoFromPostTest {

    Company dummyCompany = new Company(1, "wanted1", "Korea", "Seoul");

    Post dummyPost = new Post(dummyCompany, "Backend", 1000, "blahblah", "spring");

    @Test
    public void toDto_Throws_PostException_When_Post_Is_Null() {
      Post invalidPost = null;
      assertThrows(PostException.class, () -> postFormatter.toDto(invalidPost));
    }

    @Test
    public void toDto_Returns_PostUpdateDto() {
      PostUpdateDto dto = postFormatter.toDto(dummyPost);
      assertThat(dto.getId()).isEqualTo(dummyPost.getId());
    }
  }
}
