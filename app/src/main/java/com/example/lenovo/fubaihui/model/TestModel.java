package com.example.lenovo.fubaihui.model;

import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.NetManager;

import retrofit2.http.Path;

/**
 * Created by lenovo on 2019/9/19.
 */

public class TestModel implements ICommonModel {
    @Override
    public void getData(final int whichApi, final ICommonView presenterCallBack, Object[] params) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi){
//            case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:
//                manager.method(manager.getNetService(Config.BASEURL).getTopic(),whichApi,presenterCallBack);
//                break;
            case ApiConfig.GET_STORE_CLASSIFY:
                manager.method(manager.getNetService(Config.BASEURL1).getStoreClassify(),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_STORE_LIST:
                int cid=(int)params[0];
                manager.method(manager.getNetService(Config.BASEURL1).getStoreList(cid),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_STORE_PARTICULARS:
                int id=(int)params[0];
                manager.method(manager.getNetService(Config.BASEURL1).getStoreParticulars(id),whichApi,presenterCallBack);
                break;
        }
    }
}
