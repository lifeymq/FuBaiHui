package com.example.lenovo.fubaihui.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.activity.Fbh_ShopActivity;
import com.example.lenovo.fubaihui.activity.Inte_ShopActivity;
import com.example.lenovo.fubaihui.activity.MemberActivity;
import com.example.lenovo.fubaihui.activity.MerchantActivity;
import com.example.lenovo.fubaihui.frame.BaseFragmentMvp;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragmentMvp {


    @BindView(R.id.myban)
    Banner mMyban;
    //    @BindView(R.id.mytab)
//    TabLayout mMytab;
    @BindView(R.id.myrec)
    RecyclerView mMyrec;
    @BindView(R.id.mybtnshop1)
    RadioButton mMybtnshop1;
    @BindView(R.id.mybtnshop2)
    RadioButton mybtnshop2;
    @BindView(R.id.mybtnshop3)
    RadioButton mybtnshop3;
    @BindView(R.id.mybtnshop4)
    RadioButton mybtnshop4;
     @BindView(R.id.rg)
     RadioGroup Rg;

    private View view;
    private Unbinder unbinder;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        super.onSuccess(whichApi, successResult);


    }


    @Override
    public void initView() {
        super.initView();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("https://ws1.sinaimg.cn/large/0070oQSqgy1fxno2dvxusj30sf10nqcm.jpg");
        strings.add("https://ws1.sinaimg.cn/large/0070oQSqgy1fxno2dvxusj30sf10nqcm.jpg");
        strings.add("https://ws1.sinaimg.cn/large/0070oQSqgy1fxno2dvxusj30sf10nqcm.jpg");
        mMyban.setImages(strings).setImageLoader(new MyBanner()).start();

        changeImageSize();


        Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mybtnshop1:
                        startActivity(new Intent(getActivity(), Fbh_ShopActivity.class));
                        break;
                      case R.id.mybtnshop2:
                        startActivity(new Intent(getActivity(), Inte_ShopActivity.class));
                        break;
                      case R.id.mybtnshop3:
                        startActivity(new Intent(getActivity(), MemberActivity.class));
                        break;
                      case R.id.mybtnshop4:
                        startActivity(new Intent(getActivity(), MerchantActivity.class));
                        break;

                        }




            }
        });




//        mMytab.addTab(mMytab.newTab().setIcon(R.drawable.home_page_fbh_store).setText("福百惠商城"));
//        mMytab.addTab(mMytab.newTab().setIcon(R.drawable.home_page_silver_store).setText("银积分商城"));
//        mMytab.addTab(mMytab.newTab().setIcon(R.drawable.home_page_search_vip).setText("搜索会员"));
//        mMytab.addTab(mMytab.newTab().setIcon(R.drawable.home_page_join_us).setText("加盟商家"));

    }


    class MyBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


    private void changeImageSize() {
        //定义底部标签图片大小
        Drawable drawfirst = getResources().getDrawable(R.drawable.home_page_fbh_store);
        Drawable drawfirst2 = getResources().getDrawable(R.drawable.home_page_silver_store);
        Drawable drawfirst3 = getResources().getDrawable(R.drawable.home_page_search_vip);
        Drawable drawfirst4 = getResources().getDrawable(R.drawable.home_page_join_us);
        drawfirst.setBounds(0,0,70,70);
        drawfirst2.setBounds(0,0,70,70);
        drawfirst3.setBounds(0,0,70,70);
        drawfirst4.setBounds(0,0,70,70);
        mMybtnshop1.setCompoundDrawables(null,drawfirst,null,null);
        mybtnshop2.setCompoundDrawables(null,drawfirst2,null,null);
        mybtnshop3.setCompoundDrawables(null,drawfirst3,null,null);
        mybtnshop4.setCompoundDrawables(null,drawfirst4,null,null);

    }



}
