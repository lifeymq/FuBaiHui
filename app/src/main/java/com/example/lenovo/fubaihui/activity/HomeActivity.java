package com.example.lenovo.fubaihui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.adapters.BanViewPager;
import com.example.lenovo.fubaihui.adapters.MyVpFragmtAdapter;
import com.example.lenovo.fubaihui.fragments.CommentFragment;
import com.example.lenovo.fubaihui.fragments.HomeFragment;
import com.example.lenovo.fubaihui.fragments.MineFragment;
import com.example.lenovo.fubaihui.fragments.ShopFragment;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseMvpActivity {


    @BindView(R.id.mytoolbar)
    Toolbar mMytoolbar;
    @BindView(R.id.mytab)
    TabLayout mMytab;
    @BindView(R.id.myvp)
    BanViewPager mMyvp;

    private ArrayList<Fragment> fragments;
    private MyVpFragmtAdapter adapter;

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setUp() {
        //mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_TEST);
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
       /* switch (whichApi) {
            case ApiConfig.GET_PERSON_RANKING_RESULT_TEST:
                TeamerRankInfo info = (TeamerRankInfo) successResult;
                Log.e("睚眦", info.toString() + "");
                break;
        }*/
    }


    @Override
    public void initView() {
        super.initView();
        mMytoolbar.setTitle("");
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CommentFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MineFragment());
        adapter = new MyVpFragmtAdapter(getSupportFragmentManager(), fragments);
        mMyvp.setAdapter(adapter);
        mMytab.setupWithViewPager(mMyvp);
        mMytab.getTabAt(0).setIcon(R.drawable.home_tab1).setText("首页");
        mMytab.getTabAt(1).setIcon(R.drawable.home_tab2).setText("商品点评");
        mMytab.getTabAt(2).setIcon(R.drawable.home_tab3).setText("购物车");
        mMytab.getTabAt(3).setIcon(R.drawable.home_tab4).setText("我的");
        mMyvp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

//        titles.add("首页");
//        titles.add("商品点评");
//        titles.add("购物车");
//        titles.add("我的");
//        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable.home_tab1)).setText(titles.get(0)));
//        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable.home_tab2)).setText(titles.get(1)));
//        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable.home_tab3)).setText(titles.get(2)));
//        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable.home_tab4)).setText(titles.get(3)));


    }
}
