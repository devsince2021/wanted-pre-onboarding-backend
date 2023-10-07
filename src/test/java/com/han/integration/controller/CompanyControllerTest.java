package com.han.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.constants.EndPoint;
import com.han.controller.CompanyController;
import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.model.Company;
import com.han.service.CompanyService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

  @MockBean
  private CompanyService companyService;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  class UpdateCompany_Test {

    String validRequestBody = "{"
            + "\"id\": 1,"
            + "\"companyName\": \"wanted6\","
            + "\"country\": \"Korea\","
            + "\"city\": \"Seoul\""
            + "}";

    String invalidRequestBody = "{"
            + "\"id\": 1,"
            + "}";

    String responseBody = "{"
            + "\"id\": 1,"
            + "\"name\": \"wanted6\","
            + "\"country\": \"Korea\","
            + "\"city\": \"Seoul\""
            + "}";

    CompanyUpdateDto dummyDto = new CompanyUpdateDto(1,"wanted6", "Korea", "Seoul");
    Company dummyCompany = new Company(1, "wanted6", "Korea", "Seoul");

    @Test
    public void updateCompany_Return_InternalError_When_Exception_Occurs() throws Exception {

    }

    @Test
    public void updateCompany_Return_BadRequest_When_Request_Invalid() throws Exception {
      when(companyService.updateCompany(dummyDto)).thenReturn(dummyCompany);

      mockMvc.perform(put(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(invalidRequestBody))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCompany_Return_Response_Ok_When_Update_Success() throws Exception {
      when(companyService.updateCompany(dummyDto)).thenReturn(dummyCompany);

      mockMvc.perform(put(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(validRequestBody))
              .andExpect(status().isOk())
              .andExpect(content().json(responseBody));
    }

  }

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
