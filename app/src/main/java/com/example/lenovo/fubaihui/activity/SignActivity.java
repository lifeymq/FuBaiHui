package com.example.lenovo.fubaihui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.SignBean;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import com.example.lenovo.fubaihui.utils.SpUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.net.dplus.CommonNetImpl.UID;

public class SignActivity extends BaseMvpActivity {

    @BindView(R.id.sign_yan)
    ImageView signyan;
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
    private int code;
    private String msg;
    public static final String SP_TYPE ="sp_type";
    public static final String USER_TYPE ="user_type";
    private SignBean.DataBean data;

    public static SignActivity getInstance(Context context) {
        Intent intent = new Intent(context, SignActivity.class);
        context.startActivity(intent);
        return new SignActivity();
    }

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
        Boolean type = (Boolean) SpUtil.getParam(SP_TYPE, false);
        if (type) {
            Integer uid = (Integer) SpUtil.getParam(UID, 0);
            Integer user_type = (Integer) SpUtil.getParam(USER_TYPE, 0);
            Intent intent = new Intent(SignActivity.this, MainActivity.class);
            intent.putExtra(UID , uid+"");
            intent.putExtra(USER_TYPE , user_type+"");
            startActivity(intent);
            finish();
        }

        signyan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signPassword.getInputType()==128){
                    //隐藏
                    signPassword.setInputType(129);
                    //闭眼
                    signyan.setImageResource( R.mipmap.forget_password_eye_close );
                }else {
                    //显示
                    signPassword.setInputType(128);
                    //睁眼
                    signyan.setImageResource( R.mipmap.forget_password_eye_open );
                }
                signPassword.postInvalidate();
                Spannable text=signPassword.getText();
                Selection.setSelection(text, text.length());
            }
        } );
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.GET_SIGN:
                SignBean signBean = (SignBean) successResult;
                Log.i("睚眦",signBean.toString());
                code = signBean.getCode();
                msg = signBean.getMsg();
                if (code == 200) {
                    //账号密码输入成功
                    msg = signBean.getMsg();
                    Intent intent = new Intent(SignActivity.this, MainActivity.class);
                    data = signBean.getData();
                    intent.putExtra(UID , data.getUid()+"");
                    intent.putExtra(USER_TYPE , data.getUser_type()+"");

                    SpUtil.setParam(SP_TYPE,true);
                    SpUtil.setParam(UID, data.getUid());
                    SpUtil.setParam(USER_TYPE, data.getUser_type());

                    startActivity(intent);
                    showToast(msg+"");
                    finish();
                }else {
                    showToast(msg+"");
                }
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
                        mPresenter.getData(ApiConfig.GET_SIGN,user,password);
                        if (code == 200){
                            Intent intent0 = new Intent(SignActivity.this, MainActivity.class);
                            startActivity(intent0);
                            finish();
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
