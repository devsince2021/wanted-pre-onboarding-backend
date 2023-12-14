package com.han.unit.controller;

import com.han.controller.PostController;
import com.han.dto.PostCreateDto;
import com.han.dto.PostUpdateDto;
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
  class UpdatePostTest {
    PostUpdateDto dummyDto = new PostUpdateDto(1, 1, "Backend", 1000, "apeawef", "spring");
    PostUpdateDto dummyResult = new PostUpdateDto(1, 1, "Backend", 1000, "apeawef", "spring");

    @Test
    public void updatePost_Throws_Exception_When_Service_Throws_Exception() {
      when(postService.updatePost(dummyDto)).thenThrow(PostException.class);
      assertThrows(PostException.class, () -> postController.updatePost(dummyDto));
    }

    @Test
    public void updatePost_Throws_PostException_When_Service_Returns_Null() {
      PostUpdateDto invalidResult = null;
      when(postService.updatePost(dummyDto)).thenReturn(invalidResult);
      assertThrows(PostException.class, () -> postController.updatePost(dummyDto));
    }

    @Test
    public void updatePost_Returns_PostUpdateDto_When_Success() {
      when(postService.updatePost(dummyDto)).thenReturn(dummyResult);
      PostUpdateDto result = postController.updatePost(dummyDto);

      assertThat(result.getId()).isEqualTo(dummyResult.getId());
      assertThat(result.getPosition()).isEqualTo(dummyResult.getPosition());

    }
  }

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
