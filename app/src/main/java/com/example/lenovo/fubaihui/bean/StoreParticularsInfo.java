package com.example.lenovo.fubaihui.bean;

import java.util.List;

public class StoreParticularsInfo {
    /**
     * code : 200
     * data : {"id":"1692","title":"长白山人参茶","description":"人参茶","content":"","price":"56.00","number":"961","cover":"/Uploads/Goods/20190728/5d3d1b6414e36.jpg","price_sc":"58.00","sorts":"56.00","silver":"0.00","people":"53","reservation":"0","images":["/Uploads/Goods/20190728/5d3d1b6a8faba.jpg","/Uploads/Goods/20190728/5d3d1b725f8b8.jpg"],"is_collect":0}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1692
         * title : 长白山人参茶
         * description : 人参茶
         * content :
         * price : 56.00
         * number : 961
         * cover : /Uploads/Goods/20190728/5d3d1b6414e36.jpg
         * price_sc : 58.00
         * sorts : 56.00
         * silver : 0.00
         * people : 53
         * reservation : 0
         * images : ["/Uploads/Goods/20190728/5d3d1b6a8faba.jpg","/Uploads/Goods/20190728/5d3d1b725f8b8.jpg"]
         * is_collect : 0
         */

        private String id;
        private String title;
        private String description;
        private String content;
        private String price;
        private String number;
        private String cover;
        private String price_sc;
        private String sorts;
        private String silver;
        private String people;
        private String reservation;
        private int is_collect;
        private List<String> images;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getReservation() {
            return reservation;
        }

        public void setReservation(String reservation) {
            this.reservation = reservation;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
