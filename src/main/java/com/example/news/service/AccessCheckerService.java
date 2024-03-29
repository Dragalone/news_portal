package com.example.news.service;

import com.example.news.aop.AccessCheckType;
import com.example.news.aop.Checkable;
import jakarta.servlet.http.HttpServletRequest;

public interface AccessCheckerService {

    boolean check(HttpServletRequest request, Checkable accessible);
    AccessCheckType getType();
}
