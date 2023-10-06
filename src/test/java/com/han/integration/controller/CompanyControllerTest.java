package com.han.integration.controller;

import com.han.constants.EndPoint;
import com.han.controller.CompanyController;
import com.han.dto.CompanyCreateDto;
import com.han.model.Company;
import com.han.service.CompanyService;
import com.han.utils.SafeObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

  @MockBean
  private CompanyService companyService;
  @Autowired
  private MockMvc mockMvc;


  @Nested
  class CreateNewCompany_Test {

    String requestBody = "{"
            + "\"companyName\": \"wanted6\","
            + "\"country\": \"Korea\","
            + "\"city\": \"Seoul\""
            + "}";

    CompanyCreateDto dummyDto = new CompanyCreateDto("wanted6", "Korea", "Seoul");
    Company dummyCompany = new Company(1, "wanted6", "Korea", "Seoul");



    @Test
    public void createNewCompany_Return_True_When_Success() throws Exception {

      when(companyService.createCompany(dummyDto)).thenReturn(dummyCompany);

      mockMvc.perform(post(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(requestBody))
              .andExpect(status().isCreated())
              .andExpect(content().string("true"));
    }
  }
}
