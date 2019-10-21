package com.example.lenovo.fubaihui.frame;

import android.util.Log;

/**
 * Created by lenovo on 2019/9/19.
 */

public class CommonPresenter extends BasePresenter implements ICommonPresenter,ICommonView{

    @Override
    public void getData(int whichApi,Object... params) {
        if (getModel() != null)
            getModel().getData(whichApi,this,params);
        else Log.e("found", " null when getModel in which api:" + whichApi + "-------");
    }

    @Override
    public void onSuccess(int whichApi,Object successResult) {
        if (getView() != null)
            getView().onSuccess(whichApi,successResult);
        else Log.e("found", " null when getView in which api:" + whichApi + "-------");
    }

    @Override
    public void onFailed(int whichApi,Throwable failedResult) {
        if (getView() != null)
            getView().onFailed(whichApi,failedResult);
        else Log.e("found", " null when getView in which api:" + whichApi + "-------" +
                failedResult != null ? failedResult.getMessage() : "network error");
    }
}
