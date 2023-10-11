package com.han.controller;

import com.han.constants.EndPoint;
import com.han.dto.PostCreateDto;
import com.han.dto.PostUpdateDto;
import com.han.exception.PostException;
import com.han.model.Post;
import com.han.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(EndPoint.API)
public class PostController {

  @Autowired
  private PostService postService;

  @PostMapping(EndPoint.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Boolean createPost(@RequestBody @Valid PostCreateDto dto) {
    Post savedPost = postService.createPost(dto);
    Boolean isSuccess = savedPost != null;

    if (!isSuccess) {
      throw new PostException("Fail to create post in controller");
    }

    return isSuccess;
  }

  @PutMapping(EndPoint.POST)
  @ResponseStatus(HttpStatus.OK)
  public PostUpdateDto updatePost(@RequestBody @Valid PostUpdateDto dto) {
    PostUpdateDto result = postService.updatePost(dto);

    if (result == null) {
      throw new PostException("Fail to update post in controller");
    }

    return result;
  }
}
