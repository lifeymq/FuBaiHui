package com.example.lenovo.fubaihui.frame;

/**
 * Created by lenovo on 2019/9/19.
 */

public interface ICommonPresenter<P> {
    void getData(int whichApi, P... params);
}
