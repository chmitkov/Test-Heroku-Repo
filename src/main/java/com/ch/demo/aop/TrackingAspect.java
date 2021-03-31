package com.ch.demo.aop;

import com.ch.demo.model.entity.enums.LogType;
import com.ch.demo.service.LogService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Aspect
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Configuration
public class TrackingAspect {

    private final LogService logService;

    public TrackingAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* com.ch.demo.web.AlbumController.details(..))")
    private void detailsPointCut(){}

    @After("detailsPointCut()")
    public void afterAdvice(){
        logService.addLog(LogType.PLAY_VIDEO);
    }
}
