package com.bw.movie.bean;
/*
 *@auther:张恩
 *@Date: 2019/11/11
 *@Time:19:33
 *@Description:${DESCRIPTION}
 **/

public class RegisterBean {

    /**
     * message : 注册成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
