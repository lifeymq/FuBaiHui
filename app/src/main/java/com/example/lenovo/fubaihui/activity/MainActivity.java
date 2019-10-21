package com.example.lenovo.fubaihui.activity;
import android.util.Log;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.TeamerRankInfo;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

public class MainActivity extends BaseMvpActivity {

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setUp() {
        mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_TEST);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi){
            case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:
                TeamerRankInfo info = (TeamerRankInfo) successResult;
                Log.e("睚眦",info.toString()+"");
                break;
        }
    }
}
