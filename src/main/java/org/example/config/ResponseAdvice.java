package org.example.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.JSONResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-22;   Time: 17:29
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }
    /**
     * 执行完Controller的请求映射方法，如果是有响应体内容，可以在写入http协议响应体
     * 之前，重写
     * @param body 请求映射方法返回值
     * @param response SpringMVC封装的响应类
     * @return 返回的响应体内容：一般的做法，请求映射返回Object，可以改为其他对象
     *                                            返回String，使用json序列化
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //如果请求映射方法返回统一响应格式，就不需要再次包裹
        if(body instanceof JSONResponse)
            return body;
        //包裹为统一的响应格式
        JSONResponse json = new JSONResponse();
        json.setSuccess(true);
        json.setData(body);
        //如果是String，需要序列化
        if (body instanceof String){
            //string序列化为字符串
            try {
                objectMapper.writeValueAsString(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("json序列化出错："+json);//建议采取这种抛异常
                //e.printStackTrace();//吃异常
            }
        }
        return json;
    }
}
