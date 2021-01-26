package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-30;   Time: 0:00
 */
@Getter
@Setter
@ToString
public class JSONResponse {
    private boolean success;
    private String Code;
    private String Message;
    private Object data;
}
