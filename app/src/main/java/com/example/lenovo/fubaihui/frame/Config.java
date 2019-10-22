package com.example.lenovo.fubaihui.frame;

/**
 * Created by lenovo on 2019/9/20.
 */

public class Config {
    public static String BASEURL;
    private static int type = 1;

    /**
     * 1,外网正式服务器
     * 2，外网测试服务器
     * 3，内网测试服务器
     */
    static {
        if (type == 1){//在一种环境下可能有多个服务器
            BASEURL = "http://newwasj.zhangtongdongli.com/";
        } else if (type == 2){
            BASEURL = "http://newwasj.zhangtongdongli.com/";
        } else {
            BASEURL = "http://newwasj.zhangtongdongli.com/";
        }
    }
}
