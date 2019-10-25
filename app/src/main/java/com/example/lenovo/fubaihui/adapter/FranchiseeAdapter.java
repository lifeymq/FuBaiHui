package com.example.lenovo.fubaihui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FranchiseeAdapter extends RecyclerView.Adapter<FranchiseeAdapter.ViewHolder> {
   private List<FranchiseeInfo.DataBean> dataLists;
   private OnItemClickListener onItemClickListener;

   public FranchiseeAdapter(List<FranchiseeInfo.DataBean> dataLists) {

      this.dataLists = dataLists;
   }

   public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
      this.onItemClickListener = onItemClickListener;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
          .item_franchisee_list, parent, false));
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      Context context = holder.itemView.getContext();
      String url = null;

      if (dataLists.get(position).getLogo() != null) {
         url = "http://newwasj.zhangtongdongli.com" + dataLists.get(position).getLogo();
      }
      Glide.with(context).load(url).into(holder
          .ivFranchiseeImage);
      holder.tvFranchiseeName.setText(dataLists.get(position).getName());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (onItemClickListener!=null) {
               onItemClickListener.onItemClickListener(position);
            }
         }
      });
   }

   @Override
   public int getItemCount() {
      return dataLists.size();
   }


   public class ViewHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.iv_franchisee_image)
      ImageView ivFranchiseeImage;
      @BindView(R.id.tv_franchisee_name)
      TextView tvFranchiseeName;
      @BindView(R.id.tv_franchisee_distance)
      TextView tvFranchiseeDistance;

      public ViewHolder(View itemView) {
         super(itemView);
         ButterKnife.bind(this, itemView);
      }
   }

   public interface OnItemClickListener {
      void  onItemClickListener(int position);
   }
}
