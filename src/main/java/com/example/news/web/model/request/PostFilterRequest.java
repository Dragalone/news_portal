package com.example.news.web.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFilterRequest {

    @NotNull
    private PaginationRequest pagination;

    private String categoryName;

    private String username;
}
