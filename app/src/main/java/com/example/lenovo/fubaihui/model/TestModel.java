package com.example.lenovo.fubaihui.model;

import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.NetManager;

import java.util.Map;

/**
 * Created by lenovo on 2019/9/19.
 */

public class TestModel implements ICommonModel {
    @Override
    public void getData(final int whichApi, final ICommonView presenterCallBack, Object[] params) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi){
            case ApiConfig.GET_REGISTER:
               manager.method(manager.getNetService(Config.BASEURL).getRegister((String) params[0],
                       (String) params[1],(Number) params[2], (Number) params[3],(Number) params[4]),whichApi,presenterCallBack);
            break;
            case ApiConfig.GET_CODE:
                manager.method(manager.getNetService(Config.BASEURL).getCode((String) params[0]),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_SIGN:
                manager.method(manager.getNetService(Config.BASEURL).getSign((String) params[0],(String) params[1]),whichApi,presenterCallBack);
                break;
        }
    }
}
