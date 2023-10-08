package com.han.unit.advice;

import com.han.advice.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {


  @Mock
  private MethodArgumentNotValidException methodArgumentNotValidException;
  @Mock
  private BindingResult bindingResult;

  @InjectMocks
  private GlobalExceptionHandler globalExceptionHandler;

  @Test
  public void handleInvalidArgumentException_Returns_Error_Map() {
    FieldError dummyError = new FieldError("companyCreateDto", "country", "it is null");

    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(bindingResult.getFieldErrors()).thenReturn(List.of(dummyError));

    Map<String, String> errorMap = globalExceptionHandler.handleInvalidArgumentException(methodArgumentNotValidException);
    assertThat(errorMap).isNotEmpty();
    assertThat(errorMap.get("country")).isEqualTo(dummyError.getDefaultMessage());
  }

  @Test
  public void handleRuntimeException_Returns_Message() {
    RuntimeException dummyException = new RuntimeException("It is test");
    String message = globalExceptionHandler.handleRuntimeException(dummyException);
    assertThat(message).isEqualTo(dummyException.getMessage());
  }
}
