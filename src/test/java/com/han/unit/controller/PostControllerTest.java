package com.han.unit.controller;

import com.han.controller.PostController;
import com.han.dto.PostCreateDto;
import com.han.exception.PostException;
import com.han.model.Company;
import com.han.model.Post;
import com.han.service.PostService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

  @Mock
  private PostService postService;

  @InjectMocks
  private PostController postController;

  @Nested
  class CreatePostTest {
    PostCreateDto dummyDto = new PostCreateDto(1, "Backend", 1000, "apeawef", "spring");
    Post dummyPost = new Post(1, new Company(), "Backend", 1000, "apeawef", "spring");

    @Test
    public void createPost_Throws_RuntimeException_When_Service_Throws_RuntimeException() {
      when(postService.createPost(dummyDto)).thenThrow(RuntimeException.class);
      assertThrows(RuntimeException.class, () -> postController.createPost(dummyDto));
    }

    @Test
    public void createPost_Throws_PostException_When_Service_Throws_PostException() {
      when(postService.createPost(dummyDto)).thenThrow(PostException.class);
      assertThrows(PostException.class, () -> postController.createPost(dummyDto));
    }

    @Test
    public void createPost_Throws_PostException_When_Fail() {
      when(postService.createPost(dummyDto)).thenReturn(null);
      assertThrows(PostException.class, () -> postController.createPost(dummyDto));
    }

    @Test
    public void createPost_Return_True_When_Success() {
      when(postService.createPost(dummyDto)).thenReturn(dummyPost);
      Boolean isSuccess = postController.createPost(dummyDto);

      assertThat(isSuccess).isTrue();
    }

  }

}
