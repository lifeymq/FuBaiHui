package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.DetailsInfo;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FranchiseeDetailsActivity extends BaseMvpActivity {

   @BindView(R.id.tv_details_name)
   TextView tvDetailsName;
   @BindView(R.id.details_logo)
   ImageView detailsLogo;
   @BindView(R.id.details_title)
   TextView detailsTitle;
   @BindView(R.id.details_arrders)
   TextView detailsArrders;
   @BindView(R.id.details_logo1)
   ImageView detailsLogo1;
   @BindView(R.id.details_title1)
   TextView detailsTitle1;
   private String id;
   private String address_detail;


   @Override
   public ICommonModel setModel() {
      return new TestModel();
   }

   private String latitude;
   private String longitude;

   @Override
   public void initView() {
      super.initView();
      Intent intent = getIntent();
      FranchiseeInfo.DataBean data = (FranchiseeInfo.DataBean) intent.getSerializableExtra("data");
      String name = data.getName();
      tvDetailsName.setText(name);
      Glide.with(this).load(Config.BASEURL1 + data.getLogo())
          .placeholder(R.drawable.test1)
          .into(detailsLogo);
      detailsTitle.setText(name);
      detailsTitle1.setText(name);

      //id
      id = data.getId();

      //经度
/*      longitudes = data.getLongitude();
      //维度
      latitude = data.getLatitude();*/


   }

   @Override
   public int getLayoutId() {
      return R.layout.activity_franchisee_details;
   }

   @Override
   public void setUp() {
      mPresenter.getData(ApiConfig.POST_FRANCHISEEDETAILS, id);
   }

   @Override
   public void onSuccess(int whichApi, Object successResult) {
      switch (whichApi) {
         case ApiConfig.POST_FRANCHISEEDETAILS:
            DetailsInfo detailsInfo = (DetailsInfo) successResult;
            address_detail = detailsInfo.getData().getAddress_detail();
            detailsArrders.setText(address_detail);
            latitude = detailsInfo.getData().getLatitude();
            longitude = detailsInfo.getData().getLongitude();
            break;
      }
   }

   @OnClick(R.id.details_logo1)
   public void onClick() {
      if (!isInstallByread("com.autonavi.minimap")) {
         Toast.makeText(getApplicationContext(), "请先安装高德地图客户端", Toast.LENGTH_SHORT).show();
         return;
      }

      StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
          .append("amap");
      stringBuffer.append("&lat=").append(latitude)
          .append("&lon=").append(longitude).append("&keywords=" + address_detail)
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
