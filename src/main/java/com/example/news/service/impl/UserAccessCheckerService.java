package com.example.news.service.impl;

import com.example.news.aop.AccessCheckType;
import com.example.news.service.AbstractAccessCheckerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAccessCheckerService extends AbstractAccessCheckerService {

    @Override
    public AccessCheckType getType() {
        return AccessCheckType.USER;
    }

    @Override
    protected boolean check(Long entityId, Long userId) {
        return entityId.equals(userId);
    }
}