package com.example.lenovo.fubaihui.bean;

public class Invitationcode {

    /**
     * code : 200
     * msg : 请求成功
     * data : {"uid":"1","nickname":"管理员","pid":"694","recommend_code":"924346","name":"sj_7166"}
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
        /**
         * uid : 1
         * nickname : 管理员
         * pid : 694
         * recommend_code : 924346
         * name : sj_7166
         */

        private String uid;
        private String nickname;
        private String pid;
        private String recommend_code;
        private String name;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getRecommend_code() {
            return recommend_code;
        }

        public void setRecommend_code(String recommend_code) {
            this.recommend_code = recommend_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "uid='" + uid + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", pid='" + pid + '\'' +
                    ", recommend_code='" + recommend_code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Invitationcode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
