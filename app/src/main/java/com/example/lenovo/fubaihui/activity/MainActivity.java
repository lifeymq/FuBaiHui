package com.example.lenovo.fubaihui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.adapters.BanViewPager;
import com.example.lenovo.fubaihui.adapters.MyVpFragmtAdapter;
import com.example.lenovo.fubaihui.fragments.CommentFragment;
import com.example.lenovo.fubaihui.fragments.HomeFragment;
import com.example.lenovo.fubaihui.fragments.MineFragment;
import com.example.lenovo.fubaihui.fragments.ShopFragment;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {

   @BindView(R.id.ivsao)
   ImageView myiv;
   @BindView(R.id.mytoolbar)
   Toolbar mMytoolbar;
   @BindView(R.id.myvp)
   BanViewPager mMyvp;
   @BindView(R.id.mytab)
   TabLayout mMytab;
   @BindView(R.id.toolbartitle)
   TextView toolbartitle;

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

   }

   @Override
   public void onSuccess(int whichApi, Object successResult) {

   }


   @SuppressLint("ClickableViewAccessibility")
   @Override
   public void initView() {
      super.initView();

      mMytoolbar.setTitle("");
      setSupportActionBar(mMytoolbar);
      //设置默认返回箭头
      //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //        getSupportActionBar().setHomeButtonEnabled(true);

      fragments = new ArrayList<>();
      //        ArrayList<String> titles = new ArrayList<>();
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
         public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
         }
      });

      mMyvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            switch (position) {
               case 0:
                  toolbartitle.setText("首页");
                  break;
               case 1:
                  toolbartitle.setText("商品点评");
                  break;
               case 2:
                  toolbartitle.setText("购物车");
                  break;
               case 3:
                  toolbartitle.setText("我的");
                  break;

            }
         }

         @Override
         public void onPageSelected(int position) {

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
      });


      //        titles.add("首页");
      //        titles.add("商品点评");
      //        titles.add("购物车");
      //        titles.add("我的");
      //        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable
      // .home_tab1)).setText(titles.get(0)));
      //        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable
      // .home_tab2)).setText(titles.get(1)));
      //        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable
      // .home_tab3)).setText(titles.get(2)));
      //        mMytab.addTab(mMytab.newTab().setIcon(getResources().getDrawable(R.drawable
      // .home_tab4)).setText(titles.get(3)));


      myiv.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            //                showToast("=--=--------");
            startActivity(new Intent(MainActivity.this, QRCodeActivity.class));


         }
      });


   }


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showToast("=============");

                break;
        }


        return super.onOptionsItemSelected(item);
    }*/


}
