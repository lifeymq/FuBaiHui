package com.example.lenovo.fubaihui.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/9/19.
 */
public class TeamerRankInfo {

    /**
     * errno : 0
     * errmsg : success
     * data : {"topic_list":[{"logo_url":"http://img1.dqdgame.com/fastdfs/M00/03/04/ChM9m1weFLKAYIxbAAAH30sJJdE633.png","title":"今日热点","type":"line_two","list":[{"id":89,"content":"#火箭","scheme":"shanglan://circle/feedlist/89/#火箭"},{"id":90,"content":"#湖人","scheme":"shanglan://circle/feedlist/90/#湖人"},{"id":91,"content":"#勇士","scheme":"shanglan://circle/feedlist/91/#勇士"},{"id":93,"content":"#马刺","scheme":"shanglan://circle/feedlist/93/#马刺"},{"id":94,"content":"#凯尔特人","scheme":"shanglan://circle/feedlist/94/#凯尔特人"},{"id":95,"content":"#猛龙","scheme":"shanglan://circle/feedlist/95/#猛龙"},{"id":160,"content":"#独行侠","scheme":"shanglan://circle/feedlist/160/#独行侠"},{"id":404,"content":"#NBA季后赛","scheme":"shanglan://circle/feedlist/404/#NBA季后赛"}]},{"logo_url":"http://img1.dqdgame.com/fastdfs/M00/03/53/ChOlBlwt2QuAf3XMAAAKwoHk9Sw363.png","title":"热门球队","type":"line_four","list":[{"id":89,"content":"#火箭","scheme":"shanglan://circle/feedlist/89/#火箭"},{"id":90,"content":"#湖人","scheme":"shanglan://circle/feedlist/90/#湖人"},{"id":91,"content":"#勇士","scheme":"shanglan://circle/feedlist/91/#勇士"},{"id":92,"content":"#雷霆","scheme":"shanglan://circle/feedlist/92/#雷霆"},{"id":93,"content":"#马刺","scheme":"shanglan://circle/feedlist/93/#马刺"},{"id":94,"content":"#凯尔特人","scheme":"shanglan://circle/feedlist/94/#凯尔特人"},{"id":95,"content":"#猛龙","scheme":"shanglan://circle/feedlist/95/#猛龙"},{"id":96,"content":"#76人","scheme":"shanglan://circle/feedlist/96/#76人"},{"id":97,"content":"#雄鹿","scheme":"shanglan://circle/feedlist/97/#雄鹿"},{"id":98,"content":"#掘金","scheme":"shanglan://circle/feedlist/98/#掘金"},{"id":99,"content":"#开拓者","scheme":"shanglan://circle/feedlist/99/#开拓者"},{"id":100,"content":"#骑士","scheme":"shanglan://circle/feedlist/100/#骑士"},{"id":101,"content":"#辽宁","scheme":"shanglan://circle/feedlist/101/#辽宁"},{"id":102,"content":"#北京","scheme":"shanglan://circle/feedlist/102/#北京"},{"id":103,"content":"#广东","scheme":"shanglan://circle/feedlist/103/#广东"},{"id":104,"content":"#新疆","scheme":"shanglan://circle/feedlist/104/#新疆"},{"id":105,"content":"#广厦","scheme":"shanglan://circle/feedlist/105/#广厦"},{"id":106,"content":"#山东","scheme":"shanglan://circle/feedlist/106/#山东"},{"id":107,"content":"#上海","scheme":"shanglan://circle/feedlist/107/#上海"},{"id":108,"content":"#四川","scheme":"shanglan://circle/feedlist/108/#四川"}]},{"logo_url":"http://img1.dqdgame.com/fastdfs/M00/03/53/ChM9m1wt2SuAYGOPAAAG7BSo09Q184.png","title":"热门球员","type":"line_four","list":[{"id":51,"content":"#詹姆斯","scheme":"shanglan://circle/feedlist/51/#詹姆斯"},{"id":52,"content":"#杜兰特","scheme":"shanglan://circle/feedlist/52/#杜兰特"},{"id":53,"content":"#库里","scheme":"shanglan://circle/feedlist/53/#库里"},{"id":54,"content":"#哈登","scheme":"shanglan://circle/feedlist/54/#哈登"},{"id":59,"content":"#莱昂纳德","scheme":"shanglan://circle/feedlist/59/#莱昂纳德"},{"id":60,"content":"#欧文","scheme":"shanglan://circle/feedlist/60/#欧文"},{"id":61,"content":"#利拉德","scheme":"shanglan://circle/feedlist/61/#利拉德"},{"id":62,"content":"#恩比德","scheme":"shanglan://circle/feedlist/62/#恩比德"},{"id":63,"content":"#保罗","scheme":"shanglan://circle/feedlist/63/#保罗"},{"id":64,"content":"#韦德","scheme":"shanglan://circle/feedlist/64/#韦德"},{"id":65,"content":"#罗斯","scheme":"shanglan://circle/feedlist/65/#罗斯"},{"id":66,"content":"#东契奇","scheme":"shanglan://circle/feedlist/66/#东契奇"},{"id":75,"content":"#易建联","scheme":"shanglan://circle/feedlist/75/#易建联"},{"id":76,"content":"#郭艾伦","scheme":"shanglan://circle/feedlist/76/#郭艾伦"},{"id":77,"content":"#周琦","scheme":"shanglan://circle/feedlist/77/#周琦"},{"id":78,"content":"#丁彦雨航","scheme":"shanglan://circle/feedlist/78/#丁彦雨航"},{"id":79,"content":"#杨鸣","scheme":"shanglan://circle/feedlist/79/#杨鸣"},{"id":80,"content":"#王哲林","scheme":"shanglan://circle/feedlist/80/#王哲林"},{"id":81,"content":"#阿不都","scheme":"shanglan://circle/feedlist/81/#阿不都"},{"id":82,"content":"#翟晓川","scheme":"shanglan://circle/feedlist/82/#翟晓川"},{"id":83,"content":"#赵睿","scheme":"shanglan://circle/feedlist/83/#赵睿"},{"id":84,"content":"#韩德君","scheme":"shanglan://circle/feedlist/84/#韩德君"},{"id":85,"content":"#赵继伟","scheme":"shanglan://circle/feedlist/85/#赵继伟"},{"id":86,"content":"#胡金秋","scheme":"shanglan://circle/feedlist/86/#胡金秋"}]}]}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TopicListBean> topic_list;

        public List<TopicListBean> getTopic_list() {
            return topic_list;
        }

        public void setTopic_list(List<TopicListBean> topic_list) {
            this.topic_list = topic_list;
        }

        public static class TopicListBean {
            /**
             * logo_url : http://img1.dqdgame.com/fastdfs/M00/03/04/ChM9m1weFLKAYIxbAAAH30sJJdE633.png
             * title : 今日热点
             * type : line_two
             * list : [{"id":89,"content":"#火箭","scheme":"shanglan://circle/feedlist/89/#火箭"},{"id":90,"content":"#湖人","scheme":"shanglan://circle/feedlist/90/#湖人"},{"id":91,"content":"#勇士","scheme":"shanglan://circle/feedlist/91/#勇士"},{"id":93,"content":"#马刺","scheme":"shanglan://circle/feedlist/93/#马刺"},{"id":94,"content":"#凯尔特人","scheme":"shanglan://circle/feedlist/94/#凯尔特人"},{"id":95,"content":"#猛龙","scheme":"shanglan://circle/feedlist/95/#猛龙"},{"id":160,"content":"#独行侠","scheme":"shanglan://circle/feedlist/160/#独行侠"},{"id":404,"content":"#NBA季后赛","scheme":"shanglan://circle/feedlist/404/#NBA季后赛"}]
             */

            private String logo_url;
            private String title;
            private String type;
            private List<ListBean> list;

            public String getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(String logo_url) {
                this.logo_url = logo_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 89
                 * content : #火箭
                 * scheme : shanglan://circle/feedlist/89/#火箭
                 */

                private int id;
                private String content;
                private String scheme;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }
            }
        }
    }
}
