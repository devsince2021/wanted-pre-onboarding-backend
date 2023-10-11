package com.han.service;

import com.han.dto.PostCreateDto;
import com.han.dto.PostListDto;
import com.han.dto.PostListResDto;
import com.han.dto.PostUpdateDto;
import com.han.model.Post;

import java.util.List;

public interface PostService {
  Post createPost(PostCreateDto dto);
  PostUpdateDto updatePost(PostUpdateDto dto);
  Boolean deletePost(Integer id);
  List<PostListResDto> getList(PostListDto dto);
}
