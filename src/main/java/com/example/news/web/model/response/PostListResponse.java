package com.example.news.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponse {

    private Long id;

    private String title;

    private String text;

    private String username;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer commentsCount;

}

