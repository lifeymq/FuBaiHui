package com.example.lenovo.fubaihui.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by 暗影精灵 on 2019/10/21.
 */

public class County implements IPickerViewData {
    private String name;
    private ArrayList<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CityBean> getCityList() {
        return city;
    }

    public void setCityList(ArrayList<CityBean> city) {
        this.city = city;
    }

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }


    public static class CityBean {

        private String name;
        private ArrayList<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getArea() {
            return area;
        }

        public void setArea(ArrayList<String> area) {
            this.area = area;
        }
    }

}
