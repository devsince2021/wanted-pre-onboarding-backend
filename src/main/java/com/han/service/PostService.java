package com.han.service;

import com.han.dto.*;
import com.han.model.Post;

import java.util.List;

public interface PostService {
  Post createPost(PostCreateDto dto);

  PostUpdateDto updatePost(PostUpdateDto dto);

  Boolean deletePost(Integer id);

  List<PostListResDto> getList(PostListDto dto);

  PostDetailDto getPost(Integer id);
}
