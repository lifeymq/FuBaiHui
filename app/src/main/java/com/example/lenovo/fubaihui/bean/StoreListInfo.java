package com.example.lenovo.fubaihui.bean;

import java.util.List;

public class StoreListInfo {
    /**
     * code : 200
     * data : [{"id":"1788","category":"185","title":"合伙人大礼包充1000元送2000金积分","price":"1000.00","number":"20","cover":"/Uploads/Goods/20190802/5d43f827d4b36.jpg","price_sc":"1000.00","sorts":"1000.00","silver":"0.00","people":"1"},{"id":"1789","category":"185","title":"合伙人大礼包充1000元送1800金积分","price":"1000.00","number":"30","cover":"/Uploads/Goods/20190802/5d43f8e6a8d91.jpg","price_sc":"1000.00","sorts":"1000.00","silver":"0.00","people":"0"},{"id":"1790","category":"185","title":"合伙人大礼包充1000元送1600金积分","price":"1000.00","number":"50","cover":"/Uploads/Goods/20190802/5d43f940bb6b0.jpg","price_sc":"1000.00","sorts":"1000.00","silver":"0.00","people":"0"},{"id":"1791","category":"185","title":"合伙人大礼包充1000元送1400金积分","price":"1000.00","number":"100","cover":"/Uploads/Goods/20190802/5d43f994ad6f3.jpg","price_sc":"1000.00","sorts":"1000.00","silver":"0.00","people":"0"}]
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
         * id : 1788
         * category : 185
         * title : 合伙人大礼包充1000元送2000金积分
         * price : 1000.00
         * number : 20
         * cover : /Uploads/Goods/20190802/5d43f827d4b36.jpg
         * price_sc : 1000.00
         * sorts : 1000.00
         * silver : 0.00
         * people : 1
         */

        private String id;
        private String category;
        private String title;
        private String price;
        private String number;
        private String cover;
        private String price_sc;
        private String sorts;
        private String silver;
        private String people;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getPrice_sc() {
            return price_sc;
        }

        public void setPrice_sc(String price_sc) {
            this.price_sc = price_sc;
        }

        public String getSorts() {
            return sorts;
        }

        public void setSorts(String sorts) {
            this.sorts = sorts;
        }

        public String getSilver() {
            return silver;
        }

        public void setSilver(String silver) {
            this.silver = silver;
        }

        public String getPeople() {
            return people;
        }

        public void setPeople(String people) {
            this.people = people;
        }
    }
}
