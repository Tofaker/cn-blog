package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.AppException;
import org.example.model.JSONResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-22;   Time: 15:39
 */
//@RestControllerAdvice = @ControllerAdvice  +  @ResponseBody
@ControllerAdvice
@Slf4j//使用lombok日志注解，之后直接使用log属性完成日志打印工作
public class ExceptionAdvice {
    //
    //如果客户端请求，执行控制器方法抛出Exception异常，执行本方法
    @ExceptionHandler(AppException.class)
    //方法返回值作为响应体
    @ResponseBody
    public Object handle(AppException e){//方法参数即为捕获到的异常
        JSONResponse json = new JSONResponse();
        json.setCode(e.getCode());
        json.setMessage(e.getMessage());
        log.debug("自定义异常",e);
        return json;

        //自定义异常，保存错误码和错误消息，
        //非自定义异常（英文错误信息，堆栈信息，不给用户看）：指定一个错误码，错误消息
                // 指定一个错误码，错误消息（未知错误，联系管理员）
    }
    @ExceptionHandler(Exception.class)
    //方法返回值作为响应体
    @ResponseBody
    public Object handle2(Exception e){//捕获所有异常
        JSONResponse json = new JSONResponse();
        json.setCode("ERR000");
        json.setMessage("未知错误，请联系管理员");
        log.debug("未知错误",e);

        return json;
    }

}
