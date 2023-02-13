package com.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * 统一数据返回处理(返回的是封装类)
 */

@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof HashMap) { // 本身已经是封装好的对象
            return body;
        }
        if (body instanceof String) { // 返回类型是 String(特殊),转换器问题,不通过转换器,自己实现转换(https://blog.csdn.net/weixin_45176654/article/details/109689869?spm=1001.2014.3001.5501)
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(AjaxResult.success(body));
        }

        return AjaxResult.success(body);
    }
}
