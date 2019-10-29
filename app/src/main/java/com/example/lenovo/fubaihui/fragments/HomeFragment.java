package com.example.lenovo.fubaihui.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.activity.Fbh_ShopActivity;
import com.example.lenovo.fubaihui.activity.FranchiseeActivity;
import com.example.lenovo.fubaihui.activity.Inte_ShopActivity;
import com.example.lenovo.fubaihui.activity.IntegralActivity;
import com.example.lenovo.fubaihui.activity.MemberActivity;
import com.example.lenovo.fubaihui.bean.Home_Choiceness;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseFragmentMvp;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

//15831631087

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragmentMvp {


    @BindView(R.id.myban)
    Banner mMyban;
    //    @BindView(R.id.mytab)
//    TabLayout mMytab;
//    @BindView(R.id.myrec)
//    RecyclerView mMyrec;
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
 @BindView(R.id.iv1)
    ImageView iv1;
@BindView(R.id.iv2)
    ImageView iv2;
@BindView(R.id.iv3)
    ImageView iv3;
@BindView(R.id.iv4)
    ImageView iv4;
@BindView(R.id.tvban)
TextBannerView tvban;


    private List<Home_Choiceness.DataBean> dataBeans=new ArrayList<>();

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
        mPresenter.getData(ApiConfig.POST_HOME_CHOICENESS);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        super.onSuccess(whichApi, successResult);
        switch (whichApi) {
            case ApiConfig.POST_HOME_CHOICENESS:
                Home_Choiceness home_choiceness= (Home_Choiceness) successResult;
                List<Home_Choiceness.DataBean.JingxuanBean> jingxuan = home_choiceness.getData().getJingxuan();
                final List<Home_Choiceness.DataBean.GonggaoBean> gonggao = home_choiceness.getData().getGonggao();

                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < jingxuan.size(); i++) {
//                    showLog("ssssssssssssssssss"+Config.BASEURL1+jingxuan.get(i).getPath());
                    showLog("ssssssssssssssssss"+gonggao.get(i).getTitle());
                    strings.add(gonggao.get(i).getTitle());
                }

                Glide.with(getActivity()).load(Config.BASEURL1+jingxuan.get(0).getPath()).placeholder(R.mipmap.ic_launcher).into(iv1);
                Glide.with(getActivity()).load(Config.BASEURL1+jingxuan.get(1).getPath()).placeholder(R.mipmap.ic_launcher).into(iv2);
                Glide.with(getActivity()).load(Config.BASEURL1+jingxuan.get(2).getPath()).placeholder(R.mipmap.ic_launcher).into(iv3);
                Glide.with(getActivity()).load(Config.BASEURL1+jingxuan.get(3).getPath()).placeholder(R.mipmap.ic_launcher).into(iv4);

                tvban.setDatas(strings);
                tvban.setItemOnClickListener(new ITextBannerItemClickListener() {
                    @Override
                    public void onItemClick(String data, int position) {
                        showToast(data+"sadasdasdasd");
                    }
                });



                break;
        }

    }


    @Override
    public void initView() {
        super.initView();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("http://01imgmini.eastday.com//mobile//20191022//2019102210_ab99d9ecef1540efb10427787cd46be3_3901_cover_mwpm_03200403.jpg");
        strings.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxno2dvxusj30sf10nqcm.jpg");
        strings.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxno2dvxusj30sf10nqcm.jpg");
        mMyban.setImages(strings).setImageLoader(new MyBanner()).start();

        changeImageSize();

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
        drawfirst.setBounds(0, 0, 90, 90);
        drawfirst2.setBounds(0, 0, 90, 90);
        drawfirst3.setBounds(0, 0, 90, 90);
        drawfirst4.setBounds(0, 0, 90, 90);
        mMybtnshop1.setCompoundDrawables(null, drawfirst, null, null);
        mybtnshop2.setCompoundDrawables(null, drawfirst2, null, null);
        mybtnshop3.setCompoundDrawables(null, drawfirst3, null, null);
        mybtnshop4.setCompoundDrawables(null, drawfirst4, null, null);

    }


    @OnClick({R.id.mybtnshop1, R.id.mybtnshop2, R.id.mybtnshop3, R.id.mybtnshop4})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mybtnshop1:

                startActivity(new Intent(getActivity(), Fbh_ShopActivity.class));
                break;
            case R.id.mybtnshop2:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            case R.id.mybtnshop3:
                startActivity(new Intent(getActivity(), MemberActivity.class));
                break;
            case R.id.mybtnshop4:
                startActivity(new Intent(getActivity(), FranchiseeActivity.class));
                break;
        }
    }
}