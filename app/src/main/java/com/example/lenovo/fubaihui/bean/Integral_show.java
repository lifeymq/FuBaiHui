package com.example.lenovo.fubaihui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 暗影精灵 on 2019/10/22.
 */

public class Integral_show implements Serializable{
    /**
     * code : 200
     * msg : 请求成功
     * data : [{"id":"176","name":"唐山市丰南区潜兵战友汽车服务有限公司","logo":null,"longitude":"118.23356","latitude":"39.623778","weight":"0"},{"id":"177","name":"赛美乐润滑油","logo":"/Uploads/Garage/pic/20190804/5d467e61ebd42.png","longitude":"115.992154","latitude":"39.661222","weight":"0"},{"id":"181","name":"北京晳之密工作室","logo":"/Uploads/Garage/pic/20190805/5d480b2265d35.jpg","longitude":"116.726093","latitude":"39.960951","weight":"0"},{"id":"184","name":"孙哥","logo":"/Uploads/Goods/20190909/5d75ec5e5a967.jpg","longitude":"121.364133","latitude":"31.225672","weight":"0"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 176
         * name : 唐山市丰南区潜兵战友汽车服务有限公司
         * logo : null
         * longitude : 118.23356
         * latitude : 39.623778
         * weight : 0
         */

        private String id;
        private String name;
        private Object logo;
        private String longitude;
        private String latitude;
        private String weight;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
