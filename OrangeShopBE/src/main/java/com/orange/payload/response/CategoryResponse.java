package com.orange.payload.response;

import lombok.Data;

@Data
public class CategoryResponse {
    private String name;
    private Boolean status;
    private Long parentCategoryId;
}
