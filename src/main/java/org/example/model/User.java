package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-02;   Time: 11:23
 */
@Getter
@Setter
@ToString
public class User implements Serializable {//序列化时用到serializable接口
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
}
