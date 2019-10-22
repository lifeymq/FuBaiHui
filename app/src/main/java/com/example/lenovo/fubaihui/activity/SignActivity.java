package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends BaseMvpActivity {


    @BindView(R.id.sign_user)
    EditText signUser;
    @BindView(R.id.sign_password)
    EditText signPassword;
    @BindView(R.id.sign_button)
    Button signButton;
    @BindView(R.id.sign_register)
    TextView signRegister;
    @BindView(R.id.sign_modify)
    TextView signModify;
    @BindView(R.id.sign_box)
    CheckBox signBox;
    @BindView(R.id.sign_boxtext)
    TextView signBoxtext;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    public void setUp() {
        //mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_TEST);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        /*switch (whichApi) {
            case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:

                break;
        }*/
    }

    @OnClick({R.id.sign_button, R.id.sign_register, R.id.sign_modify, R.id.sign_box})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_button: //登录按钮
                break;
            case R.id.sign_register://立即注册
                Intent intent = new Intent(SignActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_modify: //忘记密码
                Intent intent2 = new Intent(SignActivity.this, ModifyActivity.class);
                startActivity(intent2);
                break;
            case R.id.sign_box: //CheckBox框

                if (signBox.isChecked() == true){
                    signBoxtext.setTextColor(Color.parseColor("#E34435"));
                }else {
                    signBoxtext.setTextColor(Color.parseColor("#C8C5C5"));
                }
                break;
        }
    }
}
