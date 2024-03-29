package com.example.news.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertUserRequest {

    private Long id;

    private String username;

    private String email;

    private String password;

}


