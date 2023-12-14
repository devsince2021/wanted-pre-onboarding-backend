package com.han.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto {
  @NotNull(message = "post id should be exist")
  private Integer id;

  @NotNull(message = "companyId should be exist")
  private Integer companyId;

  @NotBlank(message = "position should not be blank")
  private String position;

  private Integer compensation;

  @NotBlank(message = "job description should not be blank")
  private String jobDescription;

  @NotBlank(message = "tech stack should not be blank")
  private String techStack;

  public boolean isValid() {
    return id != null && companyId != null
            && position != null && !position.trim().isEmpty()
            && jobDescription != null && !jobDescription.trim().isEmpty()
            && techStack != null && !techStack.trim().isEmpty();
  }

}
