package com.example.lenovo.fubaihui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.StoreListInfo;

import java.util.List;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.VH> {

    private Context context;
    private List<StoreListInfo.DataBean> storeList;

    public StoreListAdapter(Context context, List<StoreListInfo.DataBean> storeList) {
        this.context = context;
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.storelist_adapter, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        Glide.with(context).load("http://newwasj.zhangtongdongli.com" + storeList.get(position).getCover()).fitCenter().into(holder.cover);
        holder.title.setText(storeList.get(position).getTitle());
        holder.price.setText("￥" + storeList.get(position).getPrice());
        holder.people.setText(storeList.get(position).getPeople() + "人付款");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView cover;
        private TextView title;
        private TextView price;
        private TextView people;

        public VH(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            people = itemView.findViewById(R.id.people);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
