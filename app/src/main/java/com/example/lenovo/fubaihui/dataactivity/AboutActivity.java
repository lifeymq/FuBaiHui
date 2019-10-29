package com.example.lenovo.fubaihui.dataactivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseMvpActivity {

    @BindView(R.id.item_img)
    ImageView itemImg;
    @BindView(R.id.item_toolname)
    TextView itemToolname;
    @BindView(R.id.item_tool)
    Toolbar itemTool;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        itemTool.setTitle("");
        itemToolname.setText("关于我们");
        setSupportActionBar(itemTool);
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @OnClick(R.id.item_img)
    public void onViewClicked() {
        finish();
    }
}
