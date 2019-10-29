package com.example.lenovo.fubaihui.bean;

public class DetailsInfo {

   /**
    * code : 200
    * msg : 请求成功
    * data : {"id":"176","name":"唐山市丰南区潜兵战友汽车服务有限公司","contacts":"李长平","phone":"15930891998",
    * "longitude":"118.23356","latitude":"39.623778","logo":"","content":"","province":"河北",
    * "city":"唐山","district":"路南区","address_detail":"唐山市路南区胜利路2号","juli":10466.37}
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
       * id : 176
       * name : 唐山市丰南区潜兵战友汽车服务有限公司
       * contacts : 李长平
       * phone : 15930891998
       * longitude : 118.23356
       * latitude : 39.623778
       * logo :
       * content :
       * province : 河北
       * city : 唐山
       * district : 路南区
       * address_detail : 唐山市路南区胜利路2号
       * juli : 10466.37
       */

      private String id;
      private String name;
      private String contacts;
      private String phone;
      private String longitude;
      private String latitude;
      private String logo;
      private String content;
      private String province;
      private String city;
      private String district;
      private String address_detail;
      private double juli;

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

      public String getContacts() {
         return contacts;
      }

      public void setContacts(String contacts) {
         this.contacts = contacts;
      }

      public String getPhone() {
         return phone;
      }

      public void setPhone(String phone) {
         this.phone = phone;
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

      public String getLogo() {
         return logo;
      }

      public void setLogo(String logo) {
         this.logo = logo;
      }

      public String getContent() {
         return content;
      }

      public void setContent(String content) {
         this.content = content;
      }

      public String getProvince() {
         return province;
      }

      public void setProvince(String province) {
         this.province = province;
      }

      public String getCity() {
         return city;
      }

      public void setCity(String city) {
         this.city = city;
      }

      public String getDistrict() {
         return district;
      }

      public void setDistrict(String district) {
         this.district = district;
      }

      public String getAddress_detail() {
         return address_detail;
      }

      public void setAddress_detail(String address_detail) {
         this.address_detail = address_detail;
      }

      public double getJuli() {
         return juli;
      }

      public void setJuli(double juli) {
         this.juli = juli;
      }
   }
}
