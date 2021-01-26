package org.example.controller;

import org.example.exception.AppException;
import org.example.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-26;   Time: 17:35
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Object login(User user, HttpServletRequest req){
        //校验用户密码
        if ("abc".equals(user.getUsername())){//模拟查找用户名
            if ("123".equals(user.getPassword())){;//模拟校验密码
                //登录成功，设置session，
                HttpSession session = req.getSession();//获取session ，获取不到就创建一个，session 一定不会是null
                session.setAttribute("user",user);
                return null;

            }else{
                throw new AppException("USELOG002","用户名或密码错误");//校验失败
            }

        }else {
            throw new AppException("USELOG001","用户名不存在");
        }

    }

    @GetMapping("/logout")
    public Object logout(HttpSession session){
        session.removeAttribute("user");
        return null;
    }
}
