package com.example.lenovo.fubaihui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.Integral_show;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

public class IntegralDetailsActivity extends AppCompatActivity implements ICommonView{

    private Integral_show.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_details);
        Bundle extras = getIntent().getExtras();
        dataBean = (Integral_show.DataBean) extras.get("data");
        TextView textView=findViewById(R.id.details_title);
        textView.setText(dataBean.getName());
        ImageView imageView=findViewById(R.id.details_logo);
        Glide.with(this).load(Config.BASEURL1+ dataBean.getLogo()).into(imageView);
        TextView textView1=findViewById(R.id.details_biao);
        textView1.setText(dataBean.getName());
        findViewById(R.id.details_logo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao();
            }
        });

    }

    private void dao() {

        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_VIEW);
        intent2.addCategory(Intent.CATEGORY_DEFAULT);
        //将功能Scheme以URI的方式传入data
        Uri uri2 = Uri.parse("qqmap://map/routeplan?type=drive&to=tvShopName&tocoord=" + dataBean.getLatitude() + "," + dataBean.getLongitude());
        intent2.setData(uri2);

        //跳转高德地图   lng目的地纬度   lat 目的地精度    tvshopName 目的地名称
        if (isAvilible(this, "com.autonavi.minimap")) {
            try {
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=新疆和田&poiname=" + dataBean.getName() + "&lat="
                        +  dataBean.getLatitude()
                        + "&lon="
                        + dataBean.getLongitude() + "&dev=0");
                this.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else if (isAvilible(this, "com.baidu.BaiduMap")) {// 传入指定应用包名
            //跳转百度地图
            try {
                Intent intent = Intent.getIntent("intent://map/direction?destination=latlng:"
                        +  dataBean.getLatitude() + ","
                        + dataBean.getLongitude() + "|name:" + dataBean.getName() + // 终点
                        "&mode=driving&" + // 导航路线方式
                        "region=武汉" + //
                        "&src=东风标致#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                this.startActivity(intent); // 启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else if (intent2.resolveActivity(this.getPackageManager()) != null) {
            //启动该页面即可     腾讯地图
            this.startActivity(intent2);
        } else {
            //跳转到应用商店去下载高德地图app
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }
    }

    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }
}
