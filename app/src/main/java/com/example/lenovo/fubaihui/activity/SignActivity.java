package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.SignBean;
import com.example.lenovo.fubaihui.frame.ApiConfig;
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
    private String password;
    private String user;
    private String phone;
    private String registerpassword;
    private int code;
    private String modifypassword;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        signUser.setText(phone);
        registerpassword = intent.getStringExtra("registerpassword");
        modifypassword = intent.getStringExtra("modifypassword");
    }

    @Override
    public void setUp() {
        mPresenter.getData(ApiConfig.GET_SIGN,phone,registerpassword);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.GET_SIGN:
                SignBean signBean = (SignBean) successResult;
                code = signBean.getCode();
               // int uid = signBean.getData().getUid();
                break;
        }
    }

    @OnClick({R.id.sign_button, R.id.sign_register, R.id.sign_modify, R.id.sign_box})
    public void onViewClicked(View view) {
        user = signUser.getText().toString();
        password = signPassword.getText().toString();
        switch (view.getId()) {
            case R.id.sign_button: //登录按钮
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
                    if (signBox.isChecked() == true) {
                        if (user.equals(phone) && password.equals(registerpassword) || password.equals(modifypassword)) {
                            Intent intent0 = new Intent(SignActivity.this, HomeActivity.class);
                            startActivity(intent0);
                            showToast("登录成功！");
                        } else {
                            showToast("输入的账号或密码不对");
                        }
                    }else{
                        showToast("您没有同意阅读。");
                    }
                }else {
                    showToast("用户名或密码不能为空");
                }

                break;
            case R.id.sign_register://立即注册
                Intent intent = new Intent(SignActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_modify: //忘记密码
                Intent intent2 = new Intent(SignActivity.this, ModifyActivity.class);
                intent2.putExtra("user",user);
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
