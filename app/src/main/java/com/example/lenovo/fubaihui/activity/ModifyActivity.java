package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.CodeBean;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import butterknife.BindView;
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
    ToggleButton modifyYan;
    @BindView(R.id.modify_button)
    Button modifyButton;
    private int code1;
    private String content;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        modifyPhone.setText(user);

        modifyYan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    maskedPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    maskedPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi){
            case ApiConfig.GET_CODE:
                CodeBean code = (CodeBean) successResult;
                content = code.getContent();
                code1 = code.getCode();
                break;
        }
    }

    @OnClick({R.id.modify_facode, R.id.modify_button ,R.id.modify_return})
    public void onViewClicked(View view) {
        String code = modifyCode.getText().toString();
        String password = maskedPassword.getText().toString();
        String facode = modifyFacode.getText().toString();
        switch (view.getId()) {
            case R.id.modify_return: //返回
                finish();
                break;
            case R.id.modify_facode: //发送验证码
                //mPresenter.getData(ApiConfig.GET_CODE,modifyPhone);
                if (TextUtils.isEmpty(modifyPhone.getText().toString())){
                    showToast("请输入发送的手机号");
                }else {
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(modifyFacode, 60000, 1000);
                    mCountDownTimerUtils.start();
                }
                break;
            case R.id.modify_button: //提交按钮
                if (TextUtils.isEmpty(code) || TextUtils.isEmpty(password)){
                    showToast("验证码或密码不能为空");
                }else{
                    showToast("修改成功");
                    Intent intent = new Intent(ModifyActivity.this, SignActivity.class);
                    intent.putExtra("modifypassword",password);
                    startActivity(intent);
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
