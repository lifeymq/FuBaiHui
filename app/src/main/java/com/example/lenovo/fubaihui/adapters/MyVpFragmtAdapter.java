package com.example.lenovo.fubaihui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.umeng.commonsdk.internal.utils.l;

import java.util.List;

public class MyVpFragmtAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public MyVpFragmtAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

//    private String[] tabs={"首页","商品点评","购物车","我的"};


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabs[position];
//    }
}
