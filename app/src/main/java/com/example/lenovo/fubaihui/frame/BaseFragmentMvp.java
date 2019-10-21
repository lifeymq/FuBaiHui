package com.example.lenovo.fubaihui.frame;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragmentMvp<M extends ICommonModel> extends BaseFragment implements ICommonView{
    public CommonPresenter mPresenter;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null, false);
        mUnbinder = ButterKnife.bind(this, inflate);
        mPresenter = new CommonPresenter();
        mPresenter.bind(this, setModel());
        setUp();
        initView();
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unBind();
    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {
      showLog("错误接口是："+whichApi+":"+failedResult != null ? failedResult.getMessage():"错误原因未知");
    }
    public void initView(){

    }
    public abstract M setModel();

    public abstract int getLayoutId();

    public abstract void setUp();

}
