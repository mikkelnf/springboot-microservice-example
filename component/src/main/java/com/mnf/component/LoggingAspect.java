package com.mnf.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Scope("prototype")
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* com.mnf..controller..*.*(..))")
    public void logBeforeApiRequest() {
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        logger.info("{}: {}", httpMethod, requestURI);
    }

    @AfterReturning(pointcut = "execution(* com.mnf..controller..*(..))", returning = "result")
    public void logAfterApiCall(JoinPoint joinPoint, Object result) {
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            int statusCode = responseEntity.getStatusCodeValue();
            String httpMethod = request.getMethod();
            String requestPath = request.getRequestURI();

            logger.info("[{}] {}: {}", statusCode, httpMethod, requestPath);
        }
    }
}
