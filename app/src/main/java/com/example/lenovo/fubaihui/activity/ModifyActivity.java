package com.example.lenovo.fubaihui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyActivity extends BaseMvpActivity {


    @BindView(R.id.modify_return)
    ImageView modifyReturn;
    @BindView(R.id.modify_cuo)
    ImageView modifyCuo;
    @BindView(R.id.register_toolbar)
    Toolbar registerToolbar;
    @BindView(R.id.modify_phone)
    TextView modifyPhone;
    @BindView(R.id.modify_code)
    EditText modifyCode;
    @BindView(R.id.modify_facode)
    TextView modifyFacode;
    @BindView(R.id.masked_password)
    EditText maskedPassword;
    @BindView(R.id.modify_yan)
    ImageView modifyYan;
    @BindView(R.id.modify_button)
    Button modifyButton;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @OnClick({R.id.modify_facode, R.id.modify_yan, R.id.modify_button ,R.id.modify_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modify_return: //返回
                finish();
                break;
            case R.id.modify_facode: //重新发送
                break;
            case R.id.modify_yan: //显示密码
                break;
            case R.id.modify_button: //提交按钮
                showToast("发送");
                break;
        }
    }
}
