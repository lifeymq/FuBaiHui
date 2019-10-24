package com.example.lenovo.fubaihui.bean;

import java.util.List;

public class Home_Choiceness {


    /**
     * code : 200
     * msg : 请求成功
     * data : {"jingxuan":[{"id":"1745","path":"/Uploads/Goods/20190729/5d3e9787142cf.jpg"},{"id":"1671","path":"/Uploads/Goods/20190712/5d281a66030d2.jpg"},{"id":"1666","path":"/Uploads/Goods/20190712/5d2815eaa0b15.jpg"},{"id":"1657","path":"/Uploads/Goods/20190712/5d2804dd50706.jpg"}],"gonggao":[{"nid":"130","title":"福百惠集团核心价值观"},{"nid":"102","title":"福百惠logo解释"},{"nid":"129","title":"福百惠APP三大模式"},{"nid":"131","title":"福百惠会员银积分获得渠道"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<JingxuanBean> jingxuan;
        private List<GonggaoBean> gonggao;

        public List<JingxuanBean> getJingxuan() {
            return jingxuan;
        }

        public void setJingxuan(List<JingxuanBean> jingxuan) {
            this.jingxuan = jingxuan;
        }

        public List<GonggaoBean> getGonggao() {
            return gonggao;
        }

        public void setGonggao(List<GonggaoBean> gonggao) {
            this.gonggao = gonggao;
        }

        public static class JingxuanBean {
            /**
             * id : 1745
             * path : /Uploads/Goods/20190729/5d3e9787142cf.jpg
             */

            private String id;
            private String path;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }

        public static class GonggaoBean {
            /**
             * nid : 130
             * title : 福百惠集团核心价值观
             */

            private String nid;
            private String title;

            public String getNid() {
                return nid;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
