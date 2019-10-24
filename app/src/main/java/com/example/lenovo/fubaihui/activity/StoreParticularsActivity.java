package com.example.lenovo.fubaihui.activity;

import android.content.Context;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.StoreParticularsInfo;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreParticularsActivity extends BaseMvpActivity implements View.OnClickListener {

    @BindView(R.id.particular_banner)
    Banner particular_banner;
    @BindView(R.id.particulars_back)
    ImageView particularsBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.minus)
    Button minus;
    @BindView(R.id.store_num)
    EditText storeNum;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.images1)
    ImageView images1;
    @BindView(R.id.images2)
    SubsamplingScaleImageView images2;
    @BindView(R.id.evaluate)
    Button evaluate;
    @BindView(R.id.store)
    Button store;
    @BindView(R.id.customer_service)
    Button customerService;
    @BindView(R.id.collect)
    Button collect;
    @BindView(R.id.order)
    TextView order;
    @BindView(R.id.add_shopping_cart)
    TextView addShoppingCart;
    @BindView(R.id.look_shopping_cart)
    TextView lookShoppingCart;
    private List<String> image = new ArrayList<>();


    @Override
    public void initView() {
        particularsBack.setOnClickListener(this);//返回
        minus.setOnClickListener(this);//减
        add.setOnClickListener(this);//加
        evaluate.setOnClickListener(this);//宝贝评价
        store.setOnClickListener(this);//店铺
        customerService.setOnClickListener(this);//客服
        collect.setOnClickListener(this);//收藏
        order.setOnClickListener(this);//立即预约
        addShoppingCart.setOnClickListener(this);//加入购物车
        lookShoppingCart.setOnClickListener(this);//查看购物车
        storeNum.setFocusable(false);
        storeNum.setFocusableInTouchMode(false);
    }

    private void banner() {
        //设置banner样式
        particular_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片集合
        particular_banner.setImages(image);
        //设置图片加载器
        particular_banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        particular_banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        particular_banner.isAutoPlay(true);
        //设置轮播时间
        particular_banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        particular_banner.start();
    }

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_particulars;
    }

    @Override
    public void setUp() {
        int id = Integer.parseInt(getIntent().getStringExtra("id"));
        mPresenter.getData(ApiConfig.GET_STORE_PARTICULARS, id);
        banner();
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.GET_STORE_PARTICULARS:
                StoreParticularsInfo info = (StoreParticularsInfo) successResult;
                StoreParticularsInfo.DataBean data = info.getData();
                image.add(data.getCover());
                banner();
                if (data.getImages().size() == 1) {
                    Glide.with(this).load("http://newwasj.zhangtongdongli.com" + data.getImages().get(0)).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.NONE).into(images1);
//                    Glide.with(this).load("http://newwasj.zhangtongdongli.com" + data.getImages().get(0)).fitCenter().into(images1);
                } else if (data.getImages().size() == 2) {
                    Glide.with(this).load("http://newwasj.zhangtongdongli.com" + data.getImages().get(0)).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.NONE).into(images1);
//                    Glide.with(this).load("http://newwasj.zhangtongdongli.com" + data.getImages().get(1)).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.NONE).into(images2);
                    images2.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
                    images2.setMinScale(1.0F);//最小显示比例
                    images2.setMaxScale(10.0F);//最大显示比例（太大了图片显示会失真，因为一般微博长图的宽度不会太宽）
                    //下载图片保存到本地
                    Glide.with(this)
                            .load("http://newwasj.zhangtongdongli.com" + data.getImages().get(1)).fitCenter().downloadOnly(new SimpleTarget<File>() {
                        @Override
                        public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                            // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                            images2.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                        }
                    });
                }
                title.setText(data.getTitle());
                price.setText("￥" + data.getPrice());
                number.setText("剩余库存量" + data.getNumber());
                description.setText("简介： " + data.getDescription());
                Log.e("上单", data.toString() + "aaaaaaaaaaaaaa");
                Log.e("打野", data.getImages().size() + "aaaaaaaaaaaaaa");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.particulars_back:
                Toast.makeText(this,"返回",Toast.LENGTH_SHORT).show();
                break;
            case R.id.minus:
                int i = Integer.parseInt(storeNum.getText().toString()) - 1;
                storeNum.setText(i+"");
                break;
            case R.id.add:
                int j = Integer.parseInt(storeNum.getText().toString()) + 1;
                storeNum.setText(j+"");
                break;
            case R.id.evaluate://宝贝评价
                Toast.makeText(this,"宝贝评价",Toast.LENGTH_SHORT).show();
                break;
            case R.id.store://店铺
                Toast.makeText(this,"店铺",Toast.LENGTH_SHORT).show();
                break;
            case R.id.customer_service://客服
                Toast.makeText(this,"客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.collect://收藏
                Toast.makeText(this,"收藏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.order://立即预约
                Toast.makeText(this,"立即预约",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_shopping_cart://加入购物车
                Toast.makeText(this,"加入购物车",Toast.LENGTH_SHORT).show();
                break;
            case R.id.look_shopping_cart://查看购物车
                Toast.makeText(this,"查看购物车",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
//            Glide.with(context).load("http://newwasj.zhangtongdongli.com" + path).into(imageView);
            Picasso.with(context).load("http://newwasj.zhangtongdongli.com" + path).into(imageView);
//            imageView.setImageResource((Integer) path);
        }
        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            return new ImageView(context);
//        }
    }

    /**
     * 清除内存缓存.
     */
    public static void clearMemoryCache(Context context) {
        // This method must be called on the main thread.
        System.gc();
        // Glide.get(context).clearMemory();
        Glide.get(context).clearMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearMemoryCache(this);
    }
}
