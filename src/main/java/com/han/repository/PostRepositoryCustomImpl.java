package com.han.repository;

import com.han.exception.PostException;
import com.han.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @Override
  public Post update(Post post) {
    Post selectedPost = entityManager.find(Post.class, post.getId());

    if (selectedPost == null) {
      throw new PostException("No Matched PostId: >> " + post.getId());
    }

    selectedPost.setCompany(post.getCompany());
    selectedPost.setPosition(post.getPosition());
    selectedPost.setCompensation(post.getCompensation());
    selectedPost.setJobDescription(post.getJobDescription());
    selectedPost.setTechStack(post.getTechStack());

    return selectedPost;
  }

}
