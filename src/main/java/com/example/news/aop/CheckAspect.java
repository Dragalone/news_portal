package com.example.news.aop;

import com.example.news.exception.AccessDeniedException;
import com.example.news.service.AccessCheckerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.MessageFormat;
import java.util.Map;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class CheckAspect {

    private final Map<AccessCheckType, AccessCheckerService> accessCheckServiceMap;

    @Before("@annotation(checkable)")
    public void check(JoinPoint joinPoint, Checkable checkable) {
        if (checkable == null) {
            throw new IllegalArgumentException("checkable is null!");
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) {
            throw new IllegalArgumentException("RequestAttributes not present!");
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        AccessCheckerService checkerService = accessCheckServiceMap.get(checkable.checkBy());

        if (checkerService == null) {
            throw new IllegalArgumentException(
                    MessageFormat.format("AccessCheckerService for type {0} not found", checkable.checkBy())
            );
        }

        if (!checkerService.check(request, checkable)) {
            throw new AccessDeniedException("Access denied for this action!");
        }
    }
}