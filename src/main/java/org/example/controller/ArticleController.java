package org.example.controller;

import org.example.data.Data;
import org.example.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-22;   Time: 22:41
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    //显示索引文章信息：还需要用户信息告诉前端是否登录
    //一般响应的数据格式：使用已有的模型，或创建新的模型
    @GetMapping("/query")
    public Object query(HttpServletRequest req){
        User user = null;
        //获取session对象，若未登录，返回null
        HttpSession session = req.getSession(false);
        if (session != null){ //session中取出user
            User get = (User)session.getAttribute("user");//user作为键保存用户
            if(get != null){
                user = get;
            }
        }
        //返回的数据，以map结构储存，articles= List<Article>,如果登录，就存放 user=用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);//
        map.put("articles", Data.allArticles());//返回手动构造的文章
        return map;
    }

    @GetMapping("/queryByUser")
    public Object queryByUser(HttpSession session){
        //数据库通过登录用户id查询对应的文章
        User user = (User) session.getAttribute("user");
        //返回模拟的文章数据,在data类中写好的
        return Data.userArticles();

    }

    @GetMapping("/query/{id}")//{id} 代表的是变量的意思
    public Object queryById(@PathVariable Integer id){//绑定url中的变量
        //数据库根据文章id查询文章信息(    模拟数据库返回数据   )
        return Data.userArticles().get(0);

    }


}
