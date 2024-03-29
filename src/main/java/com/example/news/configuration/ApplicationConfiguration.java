package com.example.news.configuration;


import com.example.news.aop.AccessCheckType;
import com.example.news.service.AccessCheckerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Map<AccessCheckType, AccessCheckerService> accessCheckerServices(Collection<AccessCheckerService> checkerServices) {
        return checkerServices.stream().collect(Collectors.toMap(AccessCheckerService::getType, Function.identity()));
    }

}
