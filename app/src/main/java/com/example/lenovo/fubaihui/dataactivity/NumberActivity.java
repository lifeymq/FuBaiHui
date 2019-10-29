package com.example.lenovo.fubaihui.dataactivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberActivity extends BaseMvpActivity {

    @BindView(R.id.item_img)
    ImageView itemImg;
    @BindView(R.id.item_toolname)
    TextView itemToolname;
    @BindView(R.id.item_tool)
    Toolbar itemTool;
    @BindView(R.id.number_phon)
    EditText numberPhon;
    @BindView(R.id.number_huocode)
    EditText numberHuocode;
    @BindView(R.id.number_code)
    TextView numberCode;
    @BindView(R.id.number_button)
    Button numberButton;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_number;
    }

    @Override
    public void initView() {
        itemTool.setTitle("");
        itemToolname.setText("修改手机号");
        itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(itemTool);
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @OnClick({R.id.item_img, R.id.number_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_img://返回
                finish();
                break;
            case R.id.number_button://确认修改
                break;
        }
    }
}
