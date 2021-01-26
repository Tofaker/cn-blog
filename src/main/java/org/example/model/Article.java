package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-22;   Time: 20:35
 */
//@Data//把所有属性重写
@Getter
@Setter
@ToString
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;//文章作者
    private Date createTime;//创建日期
    //private Integer commentId;

}
