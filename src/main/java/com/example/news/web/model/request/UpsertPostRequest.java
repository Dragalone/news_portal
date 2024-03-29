package com.example.news.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertPostRequest {

    private Long id;

    private String title;

    private String text;

}

