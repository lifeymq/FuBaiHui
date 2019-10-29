package com.example.lenovo.fubaihui.model;

import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.NetManager;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
              // manager.method(manager.getNetService(Config.BASEURL).getTopic(),whichApi,presenterCallBack);

              case ApiConfig.POST_HOME_CHOICENESS:
              manager.method(manager.getNetService(Config.BASEURL1).getHomeChoiceness(),whichApi,presenterCallBack);
            break;
            case ApiConfig.POST_SHOW_SHOPPING_CART:
                RequestBody build = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("type", (String) params[0]).addFormDataPart("uid", (String) params[1]).addFormDataPart("token", (String) params[2]).build();
                manager.method(manager.getNetService(Config.BASEURL1).getshopping_carts(build),whichApi,presenterCallBack);
                break;
            case ApiConfig.POST_TOKEN:
                 RequestBody build1 = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("uid", (String) params[0]).addFormDataPart("app_key", (String) params[1]).build();
                manager.method(manager.getNetService(Config.BASEURL1).gettoken(build1),whichApi,presenterCallBack);
            break;
            case ApiConfig.POST_MODIFY_SHOPPING_CART:
                RequestBody build2 = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("uid", (String) params[0]).addFormDataPart("goods_id", (String) params[1]).
                        addFormDataPart("number", (String) params[2]).addFormDataPart("token", (String) params[3]).build();
                manager.method(manager.getNetService(Config.BASEURL1).getModify_shopping(build2),whichApi,presenterCallBack);
                break;
            case ApiConfig.POST_DELETE_SHOPPING_CART:
                RequestBody build3 = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("uid", (String) params[0]).addFormDataPart("id", (String) params[1]).
                        addFormDataPart("token", (String) params[2]).build();
                manager.method(manager.getNetService(Config.BASEURL1).getDelete_shopping(build3),whichApi,presenterCallBack);
                break;


        }
    }
}
