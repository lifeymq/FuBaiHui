package com.example.lenovo.fubaihui.frame;

/**
 * Created by lenovo on 2019/9/20.
 */

public class Config {
    public static String BASEURL;
    public static String BASEURL1 ;
    private static int type = 1;

    /**
     * 1,外网正式服务器
     * 2，外网测试服务器
     * 3，内网测试服务器
     */
    static {
        if (type == 1){//在一种环境下可能有多个服务器
            BASEURL = "https://bkbapi.dqdgame.com/";
            BASEURL1 = "http://newwasj.zhangtongdongli.com";
        } else if (type == 2){
            BASEURL = "http://baidu.com/";
            BASEURL1 = "http://newwasj.zhangtongdongli.com";
        } else {
            BASEURL = "http://sina.com/";
            BASEURL1 = "http://sport-data.dqdgame.com/";
        }
    }
}
