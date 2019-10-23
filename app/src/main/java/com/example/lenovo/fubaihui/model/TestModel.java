package com.example.lenovo.fubaihui.model;

import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.NetManager;

/**
 * Created by lenovo on 2019/9/19.
 */

public class TestModel implements ICommonModel {
    @Override
    public void getData(final int whichApi, final ICommonView presenterCallBack, Object[] params) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi){
            case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:
                manager.method(manager.getNetService(Config.BASEURL).getTopic(),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL:
                manager.method(manager.getNetService(Config.BASEURL1).getIntegral(),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW:
                manager.method(manager.getNetService(Config.BASEURL1).getIntegral_show((String) params[0], (String) params[1],(String) params[2]),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW_POP:
                manager.method(manager.getNetService(Config.BASEURL1).getIntegral_show_pop((String) params[0]),whichApi,presenterCallBack);
                break;
        }
    }
}
