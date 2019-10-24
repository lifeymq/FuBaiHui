package com.example.lenovo.fubaihui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.StoreClassifyInfo;

import java.util.List;

public class StoreClassifyListAdapter extends BaseAdapter {

    private Context context;
    private List<StoreClassifyInfo.DataBean> classifyList;

    public StoreClassifyListAdapter(Context context, List<StoreClassifyInfo.DataBean> classifyList) {
        this.context = context;
        this.classifyList = classifyList;
    }

    @Override
    public int getCount() {
        return classifyList.size();
    }

    @Override
    public Object getItem(int position) {
        return classifyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Hodel h;
        if (view==null){
            h=new Hodel();
            view= LayoutInflater.from(context).inflate(R.layout.storeclassifylist_adapter,null);
            h.store_classify_txt=view.findViewById(R.id.store_classify_txt);
            view.setTag(h);
        }else {
            h = (Hodel) view.getTag();
        }
        h.store_classify_txt.setText(classifyList.get(position).getTitle());
        return view;
    }

    class Hodel{
        private TextView store_classify_txt;
    }
}
