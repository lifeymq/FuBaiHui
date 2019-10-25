package com.example.lenovo.fubaihui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.adapter.StoreClassifyListAdapter;
import com.example.lenovo.fubaihui.adapter.StoreListAdapter;
import com.example.lenovo.fubaihui.bean.StoreClassifyInfo;
import com.example.lenovo.fubaihui.bean.StoreListInfo;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.CommonPresenter;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuBaiHuiStoreActivity extends BaseMvpActivity implements AdapterView.OnItemClickListener, StoreListAdapter.OnItemClickListener, View.OnClickListener {

    @BindView(R.id.storelist_recyc)
    RecyclerView storelistRecyc;
    private List<StoreClassifyInfo.DataBean> classifyList = new ArrayList<>();
    private List<StoreListInfo.DataBean> storeList = new ArrayList<>();
    @BindView(R.id.fbhstore_back)
    ImageView fbhstoreBack;
    @BindView(R.id.search_box)
    EditText searchBox;
    @BindView(R.id.search_btn)
    ImageView searchBtn;
    @BindView(R.id.classify)
    ListView classify;
    @BindView(R.id.banner_classify)
    Banner bannerClassify;
    private StoreListAdapter storeListAdapter;
    private StoreClassifyListAdapter classifyListAdapter;

    private List<Integer> images = new ArrayList<>();
    @Override
    public void initView() {
        searchBox.setFocusable(false);
        searchBox.setFocusableInTouchMode(false);
        fbhstoreBack.setOnClickListener(this);
        classifyListAdapter = new StoreClassifyListAdapter(this, classifyList);
        classify.setAdapter(classifyListAdapter);
        classify.setOnItemClickListener(this);
        storeListAdapter = new StoreListAdapter(this, storeList);
        storelistRecyc.setLayoutManager(new GridLayoutManager(this,2));
        storelistRecyc.setAdapter(storeListAdapter);

        banner();

        storeListAdapter.setOnItemClickListener(this);
    }

    private void banner() {
        images.add(R.drawable.b);
        images.add(R.drawable.c);
        //设置banner样式
        bannerClassify.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        bannerClassify.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerClassify.setImages(images);
        //设置banner动画效果
        bannerClassify.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        bannerClassify.isAutoPlay(true);
        //设置轮播时间
        bannerClassify.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        bannerClassify.start();
    }

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fu_bai_hui_store;
    }

    @Override
    public void setUp() {
        mPresenter.getData(ApiConfig.GET_STORE_CLASSIFY);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.GET_STORE_CLASSIFY:
                StoreClassifyInfo info = (StoreClassifyInfo) successResult;
                List<StoreClassifyInfo.DataBean> data = info.getData();
                classifyList.addAll(data);
                classifyListAdapter.notifyDataSetChanged();
                Log.e("睚眦1", data.toString() + "aaaaaaaaaaaaaa");
                break;
            case ApiConfig.GET_STORE_LIST:
                Log.e("---","2222");
                StoreListInfo info2 = (StoreListInfo) successResult;
                List<StoreListInfo.DataBean> data2 = info2.getData();
                if (data2!=null&&data2.size()>0) {
                    storeList.addAll(data2);
                    storeListAdapter.notifyDataSetChanged();
                }
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clearMemoryCache(this);
        storeList.clear();
        mPresenter.getData(ApiConfig.GET_STORE_LIST, Integer.parseInt(classifyList.get(position).getId()));
        mPresenter = new CommonPresenter();
        mPresenter.bind(this, setModel());
        Log.e("---",classifyList.get(position).getId());
        Toast.makeText(this,position+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, StoreParticularsActivity.class);
        String id = storeList.get(position).getId();
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"返回",Toast.LENGTH_SHORT).show();
        finish();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
           imageView.setImageResource((Integer) path);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            return new ImageView(context);
        }
    }
    /** * 清除内存缓存. */
    public static void clearMemoryCache(Context context){
        // This method must be called on the main thread.
        System.gc();
        // Glide.get(context).clearMemory();
        Glide.get(context).clearMemory();
    }


}
