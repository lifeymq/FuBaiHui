package com.example.lenovo.fubaihui.design;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;


public class SmsVerifyView extends LinearLayout implements View.OnClickListener {
    private final static int DELAY = 1000;
    private final static int CYCLE = 60;

    private int time = CYCLE;

    private EditText mPhone;

    private ImageView mArea;

    private EditText mVerifyCode;

    private TextView mVerify;

    private Handler mHandler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mVerify.setText(time--+getContext().getString(R.string.verify_second));
            mHandler.postDelayed(runnable, DELAY);
            disableVerify();
            if (time < 0) {
                reset();
            }
        }
    };

    public String getPhone() {
        return mPhone.getText().toString();
    }

    public String getVerifyCode() {
        return mVerifyCode.getText().toString();
    }

    public void setVerifyCode(String pS){
        mVerifyCode.setText(pS);
    }

    public void reset() {
        resetRunnable();
        enableVerify();
    }

    public void setDefaultData(String content){
        if (!TextUtils.isEmpty(content))mPhone.setText(content);
    }

    public void disableVerify() {
        mVerify.setClickable(false);
        mVerify.setTextColor(getContext().getResources().getColor(R.color.grey_second));
    }


    public void resetRunnable() {
        time = CYCLE;
        mVerify.setText(getContext().getString(R.string.get_verify_code));
        mHandler.removeCallbacks(runnable);
    }

    public void enableVerify() {
        mVerify.setClickable(true);
        mVerify.setTextColor(getContext().getResources().getColor(R.color.green_theme));
    }

    public SmsVerifyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.sms_verify_view, this);
        mArea = findViewById(R.id.area);
        mPhone = findViewById(R.id.register_phone);
        mVerify = findViewById(R.id.register_code);
        mVerifyCode = findViewById(R.id.register_huocode);
        mVerify.setOnClickListener(this);
        mArea.setOnClickListener(this);
        mVerify.setText(getContext().getString(R.string.get_verify_code));
        mPhone.addTextChangedListener(new VerifyTextWatcher());
        disableVerify();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SmsVerifyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    class VerifyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && s.toString().length() > 10) {
                enableVerify();
            } else {
                disableVerify();
            }

        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.register_code) {
            mHandler.post(runnable);
            if (mSmsVerifyCallback != null) mSmsVerifyCallback.smsCodeSend();
        }else if (i == R.id.area) {
            if (mSmsVerifyCallback != null) mSmsVerifyCallback.countryCodeOpen();
        }
    }

    private SmsVerifyCallback mSmsVerifyCallback;

    public void setSmsVerifyCallback(SmsVerifyCallback smsVerifyCallback) {
        this.mSmsVerifyCallback = smsVerifyCallback;
    }

    public interface SmsVerifyCallback {
        void smsCodeSend();
        void countryCodeOpen();
    }
}
