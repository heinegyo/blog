package com.lrm.blog.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@ControllerAdvice//這個註解是在攔截所有標注有Controller控制器
public class ControllerExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        logger.error("Request URL : {}, Exception : {} ", request.getRequestURL(), e);
        //記錄異常資訊

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        //通過AnnotationUtils.findAnnotation來找註解
        //參數是Exception class 以及狀態類型

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
        //使用ModelAndView物件來儲存處理完後的資料以及顯示該資料的view
        //addObject放入資料
        //將資訊放入error資料夾裡的error.html
    }

}
//ModelAndView 能夠控制我們回傳一個頁面並且加入一些後端輸出到前端的資訊
//@ExceptionHandler 標示這個方法是可以做異常處理的(Exception.class)代表只要是exception級別的他都可以攔截
