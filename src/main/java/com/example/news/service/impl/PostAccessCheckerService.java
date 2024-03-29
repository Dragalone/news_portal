package com.example.news.service.impl;

import com.example.news.aop.AccessCheckType;
import com.example.news.service.AbstractAccessCheckerService;
import com.example.news.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostAccessCheckerService extends AbstractAccessCheckerService {

    private final PostService postService;

    @Override
    protected boolean check(Long entityId, Long userId) {
        return postService.existsByIdAndAuthorId(entityId, userId);
    }

    @Override
    public AccessCheckType getType() {
        return AccessCheckType.POST;
    }
}
