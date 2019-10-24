package com.example.lenovo.fubaihui.bean;

public class RegisterBean {

    /**
     * code : 200
     * msg : 注册成功
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
