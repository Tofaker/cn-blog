package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-22;   Time: 20:40
 */
@Getter
@Setter
@ToString
public class Comment {
    private Integer id;
    private String content;
    private Integer commenter;//评论者
    private Integer articleId;//绑定文章id  - 外键
    private Date createTime;//评论创建日期
}
