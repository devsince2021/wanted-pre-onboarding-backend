package com.han.service;

import com.han.dto.PostCreateDto;
import com.han.exception.PostException;
import com.han.model.Post;
import com.han.repository.PostRepository;
import com.han.service.utils.PostFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
      throw new PostException("Fail to create post23");
    }

    return savedPost;
  }

}
