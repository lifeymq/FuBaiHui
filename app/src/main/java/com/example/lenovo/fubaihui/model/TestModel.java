package com.example.lenovo.fubaihui.model;

import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.NetManager;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Path;

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
                       (String) params[1],(String) params[2],(String) params[3],(String) params[4]),whichApi,presenterCallBack);
            break;
            case ApiConfig.GET_CODE:
                manager.method(manager.getNetService(Config.BASEURL).getCode((String) params[0]),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_SIGN:
                manager.method(manager.getNetService(Config.BASEURL).getSign((String) params[0],(String) params[1]),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_MODIFY:
                manager.method(manager.getNetService(Config.BASEURL).getModify((String) params[0],(String) params[1],
                        (String) params[2],(String) params[3]),whichApi,presenterCallBack);
                break;
            case ApiConfig.GET_INVITATION:
                manager.method(manager.getNetService(Config.BASEURL).getInvitationcode((String) params[0]),whichApi,presenterCallBack);
                break;
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

           case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:
              manager.method(manager.getNetService(Config.BASEURL).getTopic(), whichApi,
                  presenterCallBack);
              break;
           case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL:
              manager.method(manager.getNetService(Config.BASEURL1).getIntegral(), whichApi,
                  presenterCallBack);
              break;
           case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW:
              manager.method(manager.getNetService(Config.BASEURL1).getIntegral_show((String)
                  params[0], (String) params[1], (String) params[2]), whichApi, presenterCallBack);
              break;
           case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW_POP:
              manager.method(manager.getNetService(Config.BASEURL1).getIntegral_show_pop((String)
                  params[0]), whichApi, presenterCallBack);
              break;

           case ApiConfig.POST_HOME_CHOICENESS:
              manager.method(manager.getNetService(Config.BASEURL1).getHomeChoiceness(), whichApi,
                  presenterCallBack);
              break;

           case ApiConfig.POST_FRANCHISEEDETAILS:
              RequestBody build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                  .addFormDataPart("id", (String) params[0])
                  .build();

              //RequestBody requestBody = RequestBody.create(MediaType.parse("application"), (String) params[0].toString());
              manager.method(manager.getNetService(Config.BASEURL1).getDetailsInfo(build), whichApi,
                  presenterCallBack);
              break;

        }
    }
}
