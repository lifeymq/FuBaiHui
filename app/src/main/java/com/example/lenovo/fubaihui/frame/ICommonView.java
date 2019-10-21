package com.example.lenovo.fubaihui.frame;

/**
 * Created by lenovo on 2019/9/19.
 */

public interface ICommonView<S>{
    void onSuccess(int whichApi, S successResult);
    void onFailed(int whichApi, Throwable failedResult);
}
