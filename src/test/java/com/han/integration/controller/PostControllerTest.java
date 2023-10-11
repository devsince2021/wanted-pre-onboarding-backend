package com.han.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.constants.EndPoint;
import com.han.controller.PostController;
import com.han.dto.PostCreateDto;
import com.han.dto.PostUpdateDto;
import com.han.model.Company;
import com.han.model.Post;
import com.han.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

  @MockBean
  private PostService postService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  class UpdatePostTest {
    private String validRequestBody = "{"
            + "\"companyId\":1,"
            + "\"position\":\"Backend\","
            + "\"compensation\":1000,"
            + "\"jobDescription\":\"apefojawpej\","
            + "\"techStack\":\"spring boot\""
            + "}";

    private String invalidRequestBody = "{"
//            + "\"companyId\":1,"
            + "\"position\":\"Backend\","
            + "\"compensation\":10000,"
            + "\"jobDescription\":\"apwoefjwapofejowpe\","
            + "\"techStack\":\"Spring boot\""
            + "}";

    private PostUpdateDto dummyDto;
    private Post dummyPost;


    @BeforeEach
    public void setUp() throws JsonProcessingException {
      dummyDto = objectMapper.readValue(validRequestBody, PostUpdateDto.class);
      dummyPost = new Post(1, new Company(), dummyDto.getPosition(),
              dummyDto.getCompensation(), dummyDto.getJobDescription(), dummyDto.getTechStack());
    }

    @Test
    public void updatePost_Response_Ok_With_PostUpdateDto() {
//      when(postService.updatePost(dummyDto)).thenReturn(dummyPost);
    }
  }


  @Nested
  class CreatePostTest {

    private String validRequestBody = "{"
            + "\"companyId\":1,"
            + "\"position\":\"Backend\","
            + "\"compensation\":1000,"
            + "\"jobDescription\":\"apefojawpej\","
            + "\"techStack\":\"spring boot\""
            + "}";

    private String invalidRequestBody = "{"
//            + "\"companyId\":1,"
            + "\"position\":\"Backend\","
            + "\"compensation\":10000,"
            + "\"jobDescription\":\"apwoefjwapofejowpe\","
            + "\"techStack\":\"Spring boot\""
            + "}";

    private PostCreateDto dummyDto;
    private Post dummyPost;


    @BeforeEach
    public void setUp() throws JsonProcessingException {
      dummyDto = objectMapper.readValue(validRequestBody, PostCreateDto.class);
      dummyPost = new Post(1, new Company(), dummyDto.getPosition(),
              dummyDto.getCompensation(), dummyDto.getJobDescription(), dummyDto.getTechStack());
    }

    @Test
    public void createPost_Response_InternalServerError_When_Service_throws() throws Exception {
      when(postService.createPost(dummyDto)).thenThrow(RuntimeException.class);

      mockMvc.perform(post(EndPoint.API + EndPoint.POST)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(validRequestBody))
              .andExpect(status().isInternalServerError());
    }

    @Test
    public void createPost_Response_BadRequest_When_Request_Invalid() throws Exception {
      mockMvc.perform(post(EndPoint.API + EndPoint.POST)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(invalidRequestBody))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void createPost_Response_Created_When_Success() throws Exception {
      when(postService.createPost(dummyDto)).thenReturn(dummyPost);

      mockMvc.perform(post(EndPoint.API + EndPoint.POST)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(validRequestBody))
              .andExpect(status().isCreated())
              .andExpect(content().string("true"));
    }
  }
}
