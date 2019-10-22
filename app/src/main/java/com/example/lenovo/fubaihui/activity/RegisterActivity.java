package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.RegisterBean;
import com.example.lenovo.fubaihui.design.SmsVerifyView;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity implements SmsVerifyView.SmsVerifyCallback{

    @BindView(R.id.register_return)
    LinearLayout registerReturn;
    @BindView(R.id.register_code)
    TextView registerCode;
    @BindView(R.id.register_ma)
    EditText registerma;
    @BindView(R.id.register_password)
    EditText registerpassword;
    @BindView(R.id.register_box)
    CheckBox registerBox;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.register_qu)
    TextView registerQu;
    @BindView(R.id.register_sign)
    LinearLayout registerSign;
    @BindView(R.id.smsVerifyView)
    SmsVerifyView mView;
    private Map<String, String> map;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void setUp() {
        /*map.put("username","18434916114");
        map.put("password","a23456");
        map.put("recommend_code","596248");
        map.put("agree","1");*/
        mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_TEST,map);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi){
            case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:
                //map = (Map<String, String>) successResult;

                break;
        }
    }

    @Override
    public void initView() {
        mView.setSmsVerifyCallback(this);

        registerQu.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //给TextView加下划线

    }


    @OnClick({R.id.register_return, R.id.register_code, R.id.register_box, R.id.register_button, R.id.register_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_return: //返回
                finish();
                break;
            case R.id.register_code: //验证码
                break;
            case R.id.register_box: //CheckBox框
                break;
            case R.id.register_button: //注册按钮
                String ma = registerma.getText().toString();
                String password = registerpassword.getText().toString();
                if (TextUtils.isEmpty(mView.getPhone()) || TextUtils.isEmpty(mView.getVerifyCode()) ||
                      TextUtils.isEmpty(ma) || TextUtils.isEmpty(password)){
                    if (TextUtils.isEmpty(mView.getPhone())){
                        showToast("请输入手机号");
                    }
                }else{

                    /*if (TextUtils.isEmpty(mView.getVerifyCode())) {
                        showToast("请输入验证码");
                        return;
                    }*/
                    if (registerBox.isChecked()){
                        showToast("注册成功");
                    }else {
                        showToast("您没有同意阅读");
                    }
                }
                break;
            case R.id.register_sign: //去登陆
                startActivity(new Intent(RegisterActivity.this, SignActivity.class));
                break;
        }
    }

    @Override
    public void smsCodeSend() {

    }

    @Override
    public void countryCodeOpen() {

    }
}
