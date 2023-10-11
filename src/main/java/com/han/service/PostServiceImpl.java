package com.han.service;

import com.han.dto.PostCreateDto;
import com.han.dto.PostListDto;
import com.han.dto.PostListResDto;
import com.han.dto.PostUpdateDto;
import com.han.exception.PostException;
import com.han.model.Post;
import com.han.repository.PostRepository;
import com.han.service.utils.PostFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostFormatter postFormatter;

  @Autowired
  private PostRepository postRepository;

  @Override
  public Post createPost(PostCreateDto dto) {
    Post post = postFormatter.toPost(dto);
    Post savedPost = postRepository.save(post);

    if (savedPost == null) {
      log.error("Error in createPost: >> " + dto);
      throw new PostException("Fail to create post");
    }

    return savedPost;
  }

  @Override
  public PostUpdateDto updatePost(PostUpdateDto dto) {
    Post post = postFormatter.toPost(dto);
    Post updatedPost = postRepository.update(post);
    PostUpdateDto result = postFormatter.toDto(updatedPost);

    if (result == null) {
      log.error("Error in updatePost: >> " + dto);
      throw new PostException("Fail to update post");
    }
    return result;
  }

  @Override
  public Boolean deletePost(Integer id) {
    try {
      postRepository.deleteById(id);
    } catch (RuntimeException ex) {
      log.error("Error in deletePost: >> " + id);
      throw new PostException("Fail to delete post");
    }

    return true;
  }

  public List<PostListResDto> getList(PostListDto dto) {
    Pageable pageable = postFormatter.toPostPageable(dto);
    List<PostListResDto> posts = postRepository.findAllWithCompany(pageable);

    return posts;
  }
}
