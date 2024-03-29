package com.example.news.service.impl;


import com.example.news.aop.AccessCheckType;
import com.example.news.service.AbstractAccessCheckerService;
import com.example.news.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentAccessCheckerService extends AbstractAccessCheckerService {

    private final CommentService commentService;

    @Override
    protected boolean check(Long entityId, Long userId) {
        return commentService.existsByIdAndUserId(entityId, userId);
    }

    @Override
    public AccessCheckType getType() {
        return AccessCheckType.COMMENT;
    }
}
