package com.lrm.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
//指定回傳的狀態碼not_found
//not_found代表他將會把這個exception 做為找不到資源的狀態
public class NotFoundException extends RuntimeException {

    public NotFoundException() { }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
