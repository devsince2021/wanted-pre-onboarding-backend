package com.han.unit.service;

import com.han.dto.PostCreateDto;
import com.han.exception.PostException;
import com.han.model.Company;
import com.han.model.Post;
import com.han.repository.PostRepository;
import com.han.service.PostServiceImpl;
import com.han.service.utils.PostFormatter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

  @Mock
  private PostFormatter postFormatter;

  @Mock
  private PostRepository postRepository;

  @InjectMocks
  private PostServiceImpl postService;

  @Nested
  class CreatePostTest {

    PostCreateDto dummyDto = new PostCreateDto(1, "Backend", 1000, "apeawef", "spring");
    Post dummyPost = new Post(new Company(), "Backend", 1000, "apeawef", "spring");
    Post dummySavedPost = new Post(1, new Company(), "Backend", 1000, "apeawef", "spring");

    @Test
    public void createPost_Throws_PostException_When_Dto_Is_Invalid() {
      PostCreateDto invalidDto = new PostCreateDto();
      when(postFormatter.toPost(invalidDto)).thenThrow(PostException.class);
      assertThrows(PostException.class, () -> postService.createPost(invalidDto));
    }

    @Test
    public void createPost_Throws_PostException_When_Dto_Is_Null() {
      PostCreateDto invalidDto = null;
      when(postFormatter.toPost(invalidDto)).thenThrow(PostException.class);
      assertThrows(PostException.class, () -> postService.createPost(invalidDto));
    }

    @Test
    public void createPost_Throws_InvalidDataAccessApiUsageException_When_Post_Invalid() {
      Post invalidPost = null;
      when(postFormatter.toPost(dummyDto)).thenReturn(invalidPost);
      when(postRepository.save(invalidPost)).thenThrow(InvalidDataAccessApiUsageException.class);
      assertThrows(InvalidDataAccessApiUsageException.class, () -> postService.createPost(dummyDto));
    }

    @Test
    public void createPost_Throws_PostException_When_Repository_Returns_Null() {
      when(postFormatter.toPost(dummyDto)).thenReturn(dummyPost);
      when(postRepository.save(dummyPost)).thenReturn(null);
      assertThrows(PostException.class, () -> postService.createPost(dummyDto));
    }

    @Test
    public void createPost_Return_Post_When_Success() {
      when(postFormatter.toPost(dummyDto)).thenReturn(dummyPost);
      when(postRepository.save(dummyPost)).thenReturn(dummySavedPost);

      Post savedPost = postService.createPost(dummyDto);

      assertThat(savedPost).isEqualTo(dummySavedPost);
    }
  }
}
