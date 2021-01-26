package org.example.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.JSONResponse;
import org.example.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-16;   Time: 17:42
 */
public class LoginInterceptor implements HandlerInterceptor {

    /*@Autowired
    private ObjectMapper objectMapper;*/   //这种写法错误，容器扫描不到，参考下面的写法，

    //在Appconfig中注册，在LoginInterceptor中构造
    private ObjectMapper objectMapper;

    public LoginInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;//objectMapper容器中拿到的对象
    }

    @Override//有登录信息，返回true 允许访问，      返回true允许继续访问，返回false禁止访问(没有登录信息)
    //返回的布尔值 代表要不要执行 controller里的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if(session != null){//获取登录时设置的用户信息
            User user = (User) session.getAttribute("user");
            if(user != null){//登录了，允许访问
                return true;
            }
        }
        //登录失败，不允许访问的业务：区分前后端
        //TODO：前端跳转登录页面，后端返回json
//        new ObjectMapper().writeValueAsString(object);//序列化对象为json字符串
        //请求的服务路径
        String servletPath = request.getServletPath();//   /apiXXX.html
        if(servletPath.startsWith("/api/")){//后端逻辑：返回json
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            //MediaType.APPLICATION_JSON_VALUE  final修饰的静态变量  等价于 application/json

            JSONResponse json = new JSONResponse();
            json.setCode("USR000");
            json.setMessage("用户没有登录，不允许访问");
            String s = objectMapper.writeValueAsString(json);//字符串化 json
            response.setStatus(HttpStatus.UNAUTHORIZED.value());//response.setStatus  返回401的状态码
            //枚举类 HttpStatus.UNAUTHORIZED.value() 等价于401
            PrintWriter pw = response.getWriter();
            pw.println(s);
            pw.flush();
        }else{
            //没有session，直接跳转到前端登录页面，提醒登录
            //前端逻辑：重定向到登录页面 /views/index.html
            //相对路径的写法，一定是请求路径作为相对位置的参照点
            //使用绝对路径来重定向，不建议使用相对路径和转发
            String schema = request.getScheme();//http
            String host = request.getServerName();//ip
            int port = request.getServerPort();//port
            String contextPath = request.getContextPath();//application Context path应用上下文路径
            String basePath = schema+"://"+host+":"+port+contextPath;
            //重定向到登录页面
            response.sendRedirect(basePath+"/index.html");
        }
        return false;
    }
}
