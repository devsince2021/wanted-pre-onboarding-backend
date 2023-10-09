package com.han.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.constants.EndPoint;
import com.han.controller.CompanyController;
import com.han.dto.CompanyCreateDto;
import com.han.dto.CompanyUpdateDto;
import com.han.exception.CompanyException;
import com.han.model.Company;
import com.han.service.CompanyService;
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

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

  @MockBean
  private CompanyService companyService;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  class GetCompanyDetailTest {
    private Integer matchedId = 1;
    private Integer unmatchedId = 7;

    private Company dummyCompany = new Company(1, "wanted6", "Korea", "Seoul");

    private String responseBody = "{"
            + "\"id\":1,"
            + "\"name\":\"wanted6\","
            + "\"country\":\"Korea\","
            + "\"city\":\"Seoul\""
            + "}";

    @Test
    public void getCompanyDetail_Response_InternalServerError_When_Exception_Occurs() throws Exception {
      when(companyService.getCompanyDetail(matchedId)).thenThrow(RuntimeException.class);
      mockMvc.perform(get(EndPoint.COMPANY + "/" + matchedId))
              .andExpect(status().isInternalServerError());
    }

    @Test
    public void getCompanyDetail_Response_BadRequest_When_Service_Throws_CompanyException() throws Exception {
      when(companyService.getCompanyDetail(matchedId)).thenThrow(CompanyException.class);
      mockMvc.perform(get(EndPoint.COMPANY + "/" + matchedId))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void getCompanyDetail_Response_BadRequest_When_No_Matched_Company() throws Exception {
      when(companyService.getCompanyDetail(unmatchedId)).thenReturn(null);
      mockMvc.perform(get(EndPoint.COMPANY + "/" + unmatchedId))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void getCompanyDetail_Response_Ok_With_Company_When_Company_Found() throws Exception {
      when(companyService.getCompanyDetail(matchedId)).thenReturn(dummyCompany);
      mockMvc.perform(get(EndPoint.COMPANY + "/" + matchedId))
              .andExpect(status().isOk())
              .andExpect(content().string(responseBody));
    }
  }

  @Nested
  class UpdateCompanyTest {

    private String validRequestBody = "{"
            + "\"id\": 1,"
            + "\"companyName\": \"wanted6\","
            + "\"country\": \"Korea\","
            + "\"city\": \"Seoul\""
            + "}";

    private String invalidRequestBody = "{"
            + "\"id\": 1"
            + "}";

    private String responseBody = "{"
            + "\"id\": 1,"
            + "\"name\": \"wanted6\","
            + "\"country\": \"Korea\","
            + "\"city\": \"Seoul\""
            + "}";

    private CompanyUpdateDto dummyDto;
    private Company dummyCompany;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
      dummyDto = objectMapper.readValue(validRequestBody, CompanyUpdateDto.class);
      dummyCompany = new Company(1, dummyDto.getCompanyName(), dummyDto.getCountry(), dummyDto.getCity());
    }

    @Test
    public void updateCompany_Return_InternalError_When_Exception_Occurs() throws Exception {
      when(companyService.updateCompany(dummyDto)).thenThrow(RuntimeException.class);

      mockMvc.perform(put(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(validRequestBody))
              .andExpect(status().isInternalServerError());
    }

    @Test
    public void updateCompany_Response_BadRequest_When_Service_Throws_CompanyException() throws Exception {
      when(companyService.updateCompany(dummyDto)).thenThrow(CompanyException.class);

      mockMvc.perform(post(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(validRequestBody))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCompany_Response_BadRequest_When_Request_Invalid() throws Exception {
      when(companyService.updateCompany(dummyDto)).thenReturn(dummyCompany);

      mockMvc.perform(put(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(invalidRequestBody))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCompany_Response_Ok_When_Update_Success() throws Exception {
      when(companyService.updateCompany(dummyDto)).thenReturn(dummyCompany);

      mockMvc.perform(put(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(validRequestBody))
              .andExpect(status().isOk())
              .andExpect(content().json(responseBody));
    }

  }

  @Nested
  class CreateCompany_Test {
    private String validRequestBody = "{"
            + "\"companyName\":\"wanted6\","
            + "\"country\":\"Korea\","
            + "\"city\":\"Seoul\""
            + "}";

    private String invalidRequestBody = "{"
            + "\"companyName\":\"wanted6\","
//              + "\"country\":\"Korea\","
            + "\"city\":\"Seoul\""
            + "}";

    private CompanyCreateDto dummyDto;
    private Company dummyCompany;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
      dummyDto = objectMapper.readValue(validRequestBody, CompanyCreateDto.class);
      dummyCompany = new Company(1, dummyDto.getCompanyName(), dummyDto.getCountry(), dummyDto.getCity());
    }

    @Test
    public void createCompany_Response_InternalServerError_When_Service_Throws() throws Exception {
      when(companyService.createCompany(dummyDto)).thenThrow(RuntimeException.class);

      mockMvc.perform(post(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(validRequestBody))
              .andExpect(status().isInternalServerError());
    }

    @Test
    public void createCompany_Response_BadRequest_When_Service_Throws_CompanyException() throws Exception {
      when(companyService.createCompany(dummyDto)).thenThrow(CompanyException.class);

      mockMvc.perform(post(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(validRequestBody))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void createCompany_Response_BadRequest_When_RequestBody_Invalid() throws Exception {
      mockMvc.perform(post(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .content(invalidRequestBody))
              .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewCompany_Return_True_When_Success() throws Exception {
      when(companyService.createCompany(dummyDto)).thenReturn(dummyCompany);

      mockMvc.perform(post(EndPoint.COMPANY)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(validRequestBody))
              .andExpect(status().isCreated())
              .andExpect(content().string("true"));
    }
  }
}
