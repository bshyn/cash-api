package ar.com.frupp.cashapi.logging;

import ar.com.frupp.cashapi.models.ApiValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("rawtypes")
@Aspect
@Component
public class HttpLogging {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMethods() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMethods() {
    }

    @Pointcut("execution(* ar.com.frupp.cashapi.config.RestExceptionHandler.handle*(..))")
    public void validations() {
    }

    @Before("postMethods() || getMethods()")
    public void logRequest(JoinPoint joinPoint) {
        Class clazz = joinPoint.getTarget().getClass();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return;
        HttpServletRequest request = attributes.getRequest();

        String classMethod = getClassMethod(joinPoint);

        logRequest(clazz, request.getMethod(), request.getRequestURI(), classMethod, getPayload(joinPoint));
    }

    @AfterReturning(value = "postMethods() || getMethods()", returning = "response")
    public void logResponse(JoinPoint joinPoint, Object response) {
        Class clazz = joinPoint.getTarget().getClass();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return;
        HttpServletRequest request = attributes.getRequest();

        String classMethod = getClassMethod(joinPoint);

        ObjectMapper mapper = new ObjectMapper();

        String responseAsStr;
        try {
            responseAsStr = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger(HttpLogging.class).warn("Exception during response logging", e);
            responseAsStr = "";
        }

        logResponse(clazz, request.getMethod(), request.getRequestURI(), classMethod,
                getPayload(joinPoint), responseAsStr);
    }

    @AfterReturning(value = "validations()", returning = "response")
    public void logValidationResponse(JoinPoint joinPoint, ResponseEntity<ApiValidation> response) {
        Class clazz = joinPoint.getTarget().getClass();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return;
        HttpServletRequest request = attributes.getRequest();

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        String responseAsStr;
        try {
            responseAsStr = mapper.writeValueAsString(response.getBody());
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger(HttpLogging.class).warn("Exception during response logging", e);
            responseAsStr = "";
        }

        logResponse(clazz, request.getMethod(), request.getRequestURI(), "",
                "", responseAsStr);
    }

    private String getClassMethod(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i] != null ? joinPoint.getArgs()[i].toString() : "null");
            builder.append(", ");
        }
        return builder.toString();
    }

    private void logRequest(Class clazz, String httpMethod, String uri, String classMethod, String payload) {
        Logger logger = LoggerFactory.getLogger(clazz);

        logger.info("==============================================================");
        logger.info("METHOD = {}", httpMethod);
        logger.info("URI = {}", uri);
        logger.info("CLASS_METHOD = {}", classMethod);
        logger.info("PAYLOAD = {}", payload);
        logger.info("==============================================================");
    }

    private void logResponse(Class clazz, String httpMethod, String uri, String classMethod,
                             String payload, String response) {
        Logger logger = LoggerFactory.getLogger(clazz);

        logger.info("==============================================================");
        logger.info("METHOD = {}", httpMethod);
        logger.info("URI = {}", uri);
        logger.info("CLASS_METHOD = {}", classMethod);
        logger.info("PAYLOAD = {}", payload);
        logger.info("RESPONSE = {}", response);
        logger.info("==============================================================");
    }
}
