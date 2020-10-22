package com.lrm.blog.aspect;


import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect//進行切面的操作
@Component//開啟元件掃描 Spring boot才可以掃描到他
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //通過這個註解來聲明這是一個切面
    //再利用execution聲明攔截哪些類
    @Pointcut("execution(* com.lrm.blog.web.*.*(..))")
    public void log() {

    }

    @Before("log()")//切入點之前
    public void deBefore(JoinPoint joinPoint) {
        // 接收請求，並記錄請求內容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //獲取請求
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("RequestLog:{}", requestLog);
    }

    @After("log()")//切入點之後
    public void doAfter() {

//        logger.info("-----doAfter---------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Result : {}" + result);
    }

    private class RequestLog {  //紀錄請求
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
