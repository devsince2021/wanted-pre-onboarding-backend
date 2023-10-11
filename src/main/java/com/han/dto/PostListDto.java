package com.han.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {

  @NotNull(message = "page should not be empty")
  private Integer page;

  @NotNull(message = "limit should not be empty")
  private Integer limit;

  private String sort = "id";
  private String order = "desc";

  public void setOrder(String order) {
    if (order.equals("asc") || order.equals("desc")) {
      this.order = order;
    }
  }

  public void setSort(String sort) {
    if (sort.equals("id")) {
      this.sort = sort;
    }
  }
}
