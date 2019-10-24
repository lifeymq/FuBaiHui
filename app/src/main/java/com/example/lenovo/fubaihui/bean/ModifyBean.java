package com.example.lenovo.fubaihui.bean;

public class ModifyBean {

    /**
     * code : 200
     * msg : 修改密码成功！
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
        return "ModifyBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
