package com.example.lenovo.fubaihui.dataactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.activity.ModifyActivity;
import com.example.lenovo.fubaihui.activity.SignActivity;
import com.example.lenovo.fubaihui.bean.CodeBean;
import com.example.lenovo.fubaihui.bean.ModifyBean;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdministrationActivity extends BaseMvpActivity {

    @BindView(R.id.item_img)
    ImageView itemImg;
    @BindView(R.id.item_toolname)
    TextView itemToolname;
    @BindView(R.id.item_tool)
    Toolbar itemTool;
    @BindView(R.id.administration_password)
    EditText administrationPassword;
    @BindView(R.id.administration_password2)
    EditText administrationPassword2;
    @BindView(R.id.administration_huocode)
    EditText administrationHuocode;
    @BindView(R.id.administration_code)
    TextView administrationCode;
    @BindView(R.id.administration_button)
    Button administrationButton;
    private String password1;
    private String password2;
    private String codename;
    private int code;
    private String content;
    private int code1;
    private String msg;
    private String phone;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_administration;
    }

    @Override
    public void initView() {
        itemTool.setTitle("");
        itemToolname.setText("修改密码");
        setSupportActionBar(itemTool);
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi){
            case ApiConfig.GET_CODE:
                CodeBean codeBean = (CodeBean) successResult;
                Log.i("睚眦",codeBean.toString());
                code = codeBean.getCode();
                if (code == 200){
                    content = codeBean.getContent();
                    showToast("发送成功,注意接收");
                }
                break;
            case ApiConfig.GET_MODIFY:
                ModifyBean modifyBean = (ModifyBean) successResult;
                Log.i("睚眦",modifyBean.toString());
                code1 = modifyBean.getCode();
                if (code1 == 200){
                    msg = modifyBean.getMsg();
                    showToast("修改成功");
                    finish();
                }else {
                    showToast(msg+"");
                }
                break;
        }
    }

    @OnClick({R.id.item_img, R.id.administration_code, R.id.administration_button})
    public void onViewClicked(View view) {
        password1 = administrationPassword.getText().toString();
        password2 = administrationPassword2.getText().toString();
        codename = administrationCode.getText().toString();
        switch (view.getId()) {
            case R.id.item_img: //返回
                finish();
                break;
            case R.id.administration_code: //获取验证码
                Log.i("睚眦","==============="+phone);
                mPresenter.getData(ApiConfig.GET_CODE, phone);
                if (code == 200){
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(administrationCode, 60000, 1000);
                    mCountDownTimerUtils.start();
                }
                break;
            case R.id.administration_button: //确认修改
                if (TextUtils.isEmpty(codename) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)){
                    showToast("验证码或密码不能为空");
                }else{
                    if (password1.equals(password2)){
                        if (codename.equals(content)){
                            mPresenter.getData(ApiConfig.GET_MODIFY,1+"", phone, password1,content);
                            if (code1 == 200){
                                finish();
                            }
                        }else {
                            showToast("验证码有误");
                        }
                    }else {
                        showToast("两次密码不一致");
                    }

                }
                break;
        }
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
            spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            mTextView.setText(spannableString);
        }

        @Override
        public void onFinish() {
            mTextView.setText("重新发验证码");
            mTextView.setClickable(true);//重新获得点击
            mTextView.setTextColor(Color.parseColor("#000000"));
        }
    }
}
