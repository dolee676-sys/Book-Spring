package com.example.borrow.common;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice

public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFound(NotFoundException e){
        return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String,Object>> conflict(IllegalStateException e){
        return ResponseEntity.status(409).body((Map.of("error",e.getMessage())));
    }

    //参数不合法
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> badRequest(IllegalArgumentException e){
        return ResponseEntity.status(400).body(Map.of("error",e.getMessage()));

    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> integrity(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body(Map.of("error", "用户或者图书不存在"));//400
    }
    // 路径写错（比如 /api/borroww）时会走到这里。不单独处理就会被下面的 Exception 兜成 500
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<Map<String,Object>> noResource(org.springframework.web.servlet.resource.NoResourceFoundException e) {
        return ResponseEntity.status(404).body(Map.of("error", "接口不存在：" + e.getResourcePath()));
    }

    @ExceptionHandler(Exception.class)                        // 接所有剩余异常
    public ResponseEntity<Map<String, Object>> other(Exception e) {  // 入参
        e.printStackTrace();                                  // 打堆栈到控制台，方便排查真正原因
        return ResponseEntity.status(500).body(Map.of("error", "服务器内部错误"));        // 500 + 通用提示
    }
}















