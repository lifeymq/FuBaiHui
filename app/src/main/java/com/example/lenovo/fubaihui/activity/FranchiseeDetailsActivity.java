package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FranchiseeDetailsActivity extends BaseMvpActivity {

   @BindView(R.id.tv_details_name)
   TextView tvDetailsName;
   @BindView(R.id.tv_map)
   TextView tvMap;

   @Override
   public ICommonModel setModel() {
      return null;
   }

   private String longitudes;
   private String latitude;

   @Override
   public void initView() {
      super.initView();
      Intent intent = getIntent();
      FranchiseeInfo.DataBean data = (FranchiseeInfo.DataBean) intent.getSerializableExtra("data");
      String name = data.getName();
      tvDetailsName.setText(name);
      //id
      String id = data.getId();
      //经度
      longitudes = data.getLongitude();
      //维度
      latitude = data.getLatitude();


   }

   @Override
   public int getLayoutId() {
      return R.layout.activity_franchisee_details;
   }

   @Override
   public void setUp() {

   }

   @Override
   public void onSuccess(int whichApi, Object successResult) {

   }

   @OnClick(R.id.tv_map)
   public void onClick() {
      if (!isInstallByread("com.autonavi.minimap")) {
         Toast.makeText(getApplicationContext(), "请先安装高德地图客户端", Toast.LENGTH_SHORT).show();
         return;
      }

      StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
          .append("amap");
      stringBuffer.append("&lat=").append(latitude)
          .append("&lon=").append(longitudes).append("&keywords=" + "北京三里屯")
          .append("&dev=").append(0)
          .append("&style=").append(2);
      Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
      intent.setPackage("com.autonavi.minimap");
      startActivity(intent);

   }

   private boolean isInstallByread(String packageName) {
      return new File("/data/data/" + packageName).exists();
   }
}
