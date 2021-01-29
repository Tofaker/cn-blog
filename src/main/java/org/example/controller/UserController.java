package org.example.controller;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(User user, HttpServletRequest req){
        //user是浏览器界面输入的账号密码，根据账号，在数据库查用户
        User exist = userService.query(user.getUsername());//业务类userService中调方法query 查询传入对象user的name
        //数据库中查到了 exist 不为空，查不到 exist = null    exist 即为数据库中存在的User类的实例

        //校验用户密码
        if (exist != null){//模拟查找用户名
            if (exist.getPassword().equals(user.getPassword())){;//校验密码
                //登录成功，设置session，
                HttpSession session = req.getSession();//获取session ，获取不到就创建一个，session 一定不会是null
                session.setAttribute("user",exist); //把session设置到数据库查出来的User类的实例中
                //因为exist 中存在用户其他信息(头像等)，所以返回给前端exist比较好，所以把session设置到exist中
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
