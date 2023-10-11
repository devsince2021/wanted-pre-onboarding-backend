package com.han.service;

import com.han.dto.PostCreateDto;
import com.han.dto.PostUpdateDto;
import com.han.model.Post;

public interface PostService {
  Post createPost(PostCreateDto dto);

  PostUpdateDto updatePost(PostUpdateDto dto);
}
