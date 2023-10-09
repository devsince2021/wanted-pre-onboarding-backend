package com.han.service;

import com.han.dto.PostCreateDto;
import com.han.model.Post;

public interface PostService {
  Post createPost(PostCreateDto dto);
}
