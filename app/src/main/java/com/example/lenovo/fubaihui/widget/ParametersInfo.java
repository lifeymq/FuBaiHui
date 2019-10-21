package com.example.lenovo.fubaihui.widget;

import java.io.Serializable;


public class ParametersInfo implements Serializable {
    private static final long serialVersionUID = 3047872782102167560L;
    public String __timestamp;
    public String deviceToken;
    public String token;

    public ParametersInfo(){}
    public ParametersInfo(String p__timestamp, String pDeviceToken) {
        __timestamp = p__timestamp;
        deviceToken = pDeviceToken;
    }
}
