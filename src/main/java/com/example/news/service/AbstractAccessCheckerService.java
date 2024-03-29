package com.example.news.service;

import com.example.news.aop.Checkable;
import com.example.news.exception.AccessDeniedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.annotation.Retention;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public abstract class AbstractAccessCheckerService implements AccessCheckerService{
    private static final String ID = "id";


    @Override
    @SuppressWarnings("unchecked")
    public boolean check(HttpServletRequest request, Checkable accessible) {



        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long id = Long.parseLong(pathVariables.get(ID));
        if (request.getHeader("userId") == null){
            throw new AccessDeniedException("Needs userId header in request!");
        }
        Long userId = Long.parseLong(request.getHeader("userId"));

        return check(id, userId);
    }

    protected abstract boolean check(Long entityId, Long userId);

}
