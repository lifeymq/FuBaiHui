package com.example.lenovo.fubaihui.bean;

import java.util.List;

public class StoreClassifyInfo {
    /**
     * code : 200
     * data : [{"id":"216","title":"321312321"},{"id":"186","title":"茶品系列"},{"id":"185","title":"共享商城"},{"id":"184","title":"营养滋补"},{"id":"183","title":"汽车养护"},{"id":"179","title":"智能家电"},{"id":"178","title":"红酒系列"},{"id":"177","title":"健康保健"},{"id":"172","title":"化妆精品"},{"id":"165","title":"服装"},{"id":"164","title":"车品"},{"id":"163","title":"数码"},{"id":"162","title":"运动"},{"id":"160","title":"洗护"},{"id":"159","title":"生鲜"},{"id":"158","title":"食品"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 216
         * title : 321312321
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
