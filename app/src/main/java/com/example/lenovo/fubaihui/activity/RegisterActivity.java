package com.example.lenovo.fubaihui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.CodeBean;
import com.example.lenovo.fubaihui.bean.Invitationcode;
import com.example.lenovo.fubaihui.bean.RegisterBean;
import com.example.lenovo.fubaihui.design.SmsVerifyView;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity implements SmsVerifyView.SmsVerifyCallback{

    @BindView(R.id.register_return)
    LinearLayout registerReturn;
    @BindView(R.id.register_code)
    TextView registerCode;
    @BindView(R.id.register_ma)
    EditText registerma;
    @BindView(R.id.register_phone)
    EditText registerphone;
    @BindView(R.id.register_huocode)
    EditText registerhuocode;
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
    private String ma;
    private String password;
    private String huocode;
    private String phone;
    private String content;
    private String msg;
    private int code1;
    private int code;
    private String msg1;
    private String recommend_code;
    private String uid;

    public static RegisterActivity getInstance(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
        return new RegisterActivity();
    }

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
        mPresenter.getData(ApiConfig.GET_INVITATION,1+"");
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi){
            case ApiConfig.GET_REGISTER:
                RegisterBean registerBean = (RegisterBean) successResult;
                code1 = registerBean.getCode();
                Log.i("睚眦",registerBean.toString());
                msg = registerBean.getMsg();
                if (code1 == 200){
                    Intent intent = new Intent(RegisterActivity.this, SignActivity.class);
                    startActivity(intent);
                    showToast(msg+"");
                    finish();
                } else {
                    showToast(msg+"");
                }
                break;
            case ApiConfig.GET_CODE:
                CodeBean codeBean = (CodeBean) successResult;
                Log.i("睚眦",codeBean.toString());
                code = codeBean.getCode();
                if (code == 200){
                    content = codeBean.getContent();
                    showToast("发送成功,注意接收");
                }else {
                    showToast("请输入正确的手机号");
                }
                break;
            case ApiConfig.GET_INVITATION:
                Invitationcode invitationcode = (Invitationcode) successResult;
                Log.i("睚眦",invitationcode.toString());
                code = invitationcode.getCode();
                msg1 = invitationcode.getMsg();
                if (code == 200){
                    recommend_code = invitationcode.getData().getRecommend_code();
                    uid = invitationcode.getData().getUid();
                }else {
                    showToast(msg1+"");
                }
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
        phone = registerphone.getText().toString();
        huocode = registerhuocode.getText().toString();
        password = registerpassword.getText().toString();
        ma = registerma.getText().toString();
        switch (view.getId()) {
            case R.id.register_return: //返回键
                finish();
                break;
            case R.id.register_code: //验证码
                mPresenter.getData(ApiConfig.GET_CODE, phone);
                    if (code == 200){
                        RegisterActivity.CountDownTimerUtils mCountDownTimerUtils = new RegisterActivity.CountDownTimerUtils(registerCode, 60000, 1000);
                        mCountDownTimerUtils.start();
                    }
                break;
            case R.id.register_box: //CheckBox框
                break;
            case R.id.register_button: //注册按钮
                if (TextUtils.isEmpty(mView.getPhone()) || TextUtils.isEmpty(mView.getVerifyCode()) ||
                      TextUtils.isEmpty(ma) || TextUtils.isEmpty(password)){
                   showToast("不能为空");
                }else{
                    if (registerBox.isChecked()==true){
                        if (huocode.equals(content)){
                            mPresenter.getData(ApiConfig.GET_REGISTER,phone,password,recommend_code,uid,content);
                            if (code1 == 200){
                                Intent intent = new Intent(RegisterActivity.this, SignActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        showToast("您没有同意阅读");
                    }
                }
                break;
            case R.id.register_sign: //去登陆
                startActivity(new Intent(RegisterActivity.this,SignActivity.class ));
                finish();
                break;
        }
    }

    @Override
    public void smsCodeSend() {

    }

    @Override
    public void countryCodeOpen() {

    }
    public class CountDownTimerUtils extends CountDownTimer {
        private TextView mTextView;

        /**
         * @param millisInFuture  The number of millis in the future from the call
         *             to {@link #start()} until the countdown is done and {@link #onFinish()}
         *             is called.
         * @param countDownInterval The interval along the way to receive
         *             {@link #onTick(long)} callbacks.
         */
        public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.mTextView = textView;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setClickable(false); //设置不可点击
            mTextView.setText(millisUntilFinished / 1000 + "后重新发送"); //设置倒计时时间
            mTextView.setTextColor(Color.parseColor("#C8C5C5")); //设置按钮为灰
            SpannableString spannableString = new SpannableString(mTextView.getText().toString());
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(span, 0, 2 , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            mTextView.setText(spannableString);
        }

        @Override
        public void onFinish() {
            mTextView.setText("重新获取");
            mTextView.setClickable(true);//重新获得点击
            mTextView.setTextColor(Color.parseColor("#1AAC19"));
        }
    }
}
