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

public class NameActivity extends BaseMvpActivity {


    @BindView(R.id.item_img)
    ImageView itemImg;
    @BindView(R.id.item_toolname)
    TextView itemToolname;
    @BindView(R.id.item_tool)
    Toolbar itemTool;
    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.name_imag)
    ImageView nameImag;
    @BindView(R.id.name_preservation)
    Button namePreservation;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_name;
    }

    @Override
    public void initView() {
        itemTool.setTitle("");
        itemToolname.setText("修改昵称");
        setSupportActionBar(itemTool);
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @OnClick({R.id.item_img, R.id.name_imag, R.id.name_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_img: //返回
                finish();
                break;
            case R.id.name_imag://x图片
                nameEdit.setText("");
                break;
            case R.id.name_preservation://保存按钮
                break;
        }
    }
}
