package com.example.lenovo.fubaihui.frame;

/**
 * Created by lenovo on 2019/9/19.
 */

public interface ICommonModel<P> {
    void getData(int whichApi, ICommonView presenterCallBack, P... params);
}
