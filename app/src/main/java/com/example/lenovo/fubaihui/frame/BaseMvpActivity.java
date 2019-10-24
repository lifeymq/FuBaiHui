package com.example.lenovo.fubaihui.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;


public abstract class BaseMvpActivity<M extends ICommonModel> extends BaseActivity implements ICommonView{
    public CommonPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = new CommonPresenter();
        mPresenter.bind(this, setModel());

        initView();
        setUp();
    }

    public  void initView(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBind();
    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {
        showLog("错误接口是："+"GET_STORE_CLASSIFY"+
                failedResult.getMessage());
    }

    public abstract M setModel();

    public abstract int getLayoutId();

    public abstract void setUp();
}
