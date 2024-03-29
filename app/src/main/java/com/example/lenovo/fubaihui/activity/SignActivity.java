package com.example.lenovo.fubaihui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.yiyatech.utils.SharedPrefrenceUtils;
import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.net.dplus.CommonNetImpl.UID;

public class SignActivity extends BaseMvpActivity {

    private static final String USER = "user";
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
    private String users;
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
        initPermission();
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
    private void initPermission() {
        String[] mPermissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        //先判断版本号是否在23（6.0）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查自身的权限是否设置
            int i = ContextCompat.checkSelfPermission(this, mPermissions[0]);
            if (i != PackageManager.PERMISSION_GRANTED) {//没有授权
                //请求授权
                ActivityCompat.requestPermissions(this, mPermissions, 1);
            }
        }
        //先判断版本号是否在23（6.0）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查自身的权限是否设置
            int i = ContextCompat.checkSelfPermission(this, mPermissions[1]);
            if (i != PackageManager.PERMISSION_GRANTED) {//没有授权
                //请求授权
                ActivityCompat.requestPermissions(this, mPermissions, 2);
            }
        }
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
        users = signUser.getText().toString();
        password = signPassword.getText().toString();
        switch (view.getId()) {
            case R.id.sign_button: //登录按钮
                if (!TextUtils.isEmpty(users) && !TextUtils.isEmpty(password)){
                    SharedPrefrenceUtils.saveString(this,USER,users);
                    if (signBox.isChecked() == true) {
                        mPresenter.getData(ApiConfig.GET_SIGN,users,password);
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
                intent2.putExtra("user",users);
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
