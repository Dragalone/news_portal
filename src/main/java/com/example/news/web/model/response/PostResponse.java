package com.example.news.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long id;

    private String title;

    private String text;

    private String username;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer commentsCount;

    private List<CommentResponse> comments = new ArrayList<>();

}
