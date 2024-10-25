package com.example.planet.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
class LoggingAspect {

    @Before("execution(public org.springframework.data.domain.Page com.example.planet.service.DiscoverySourceService.getAllDiscoverySources(int, int))")
    public void beforeGetObjectAdvice(){
        System.out.println("Before getObject Advice");
    }

    @Before("execution(public org.springframework.data.domain.Page com.example.planet.service.StarObjectService.getAllStarObjects(int, int))")
    public void beforeGetStarObjectAdvice(){
        System.out.println("Before getStarObject Advice");
    }
}