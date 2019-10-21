package com.example.lenovo.fubaihui.frame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements ICommonView {
    private static final String TAG = "BaseFragment";
    private CommonPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this , view);
        presenter = new CommonPresenter();
        initData();
        return view;
    }

    private void initData() {

    }


    public void  showLog(Object content){
        Log.e(TAG, "睚眦: "+content.toString() );
    }

    public void showToast(String content) {
        Toast.makeText(getContext().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    public abstract int getLayoutId();
    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }
}
