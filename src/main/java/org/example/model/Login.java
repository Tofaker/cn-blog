package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-02;   Time: 18:05
 */
@Getter
@Setter
@ToString
public class Login {
    private Boolean success;
    private String data;
}
