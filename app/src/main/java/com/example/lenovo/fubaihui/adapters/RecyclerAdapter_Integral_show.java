package com.example.lenovo.fubaihui.adapters;

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
import com.example.lenovo.fubaihui.bean.Integral_show;
import com.example.lenovo.fubaihui.frame.Config;

import java.util.ArrayList;

/**
 * Created by 暗影精灵 on 2019/10/22.
 */

public class RecyclerAdapter_Integral_show extends RecyclerView
    .Adapter<RecyclerAdapter_Integral_show.ViewHolder> {
   private ArrayList<Integral_show.DataBean> list;

   public RecyclerAdapter_Integral_show(ArrayList<Integral_show.DataBean> list) {
      this.list = list;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                      int viewType) {
      View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout
          .item_integral_show, parent, false);
      return new ViewHolder(inflate);

   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, final
   int position) {

      Context context = holder.itemView.getContext();
      if (list.get(position).getLogo() != null) {

         Glide.with(context).load(Config.BASEURL1 + list.get(position).getLogo())
             .placeholder(R.drawable.test1)
             .into(holder
                 .imageView);
      }
      holder.textView1.setText(list.get(position).getName());
      holder.textView2.setText(list.get(position).getWeight());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            pull.pu(position);
         }
      });
   }

   @Override
   public int getItemCount() {
      return list.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private TextView textView1;
      private TextView textView2;

      public ViewHolder(View itemView) {
         super(itemView);
         imageView = itemView.findViewById(R.id.item_integral_show_img);
         textView1 = itemView.findViewById(R.id.item_integral_show_text1);
         textView2 = itemView.findViewById(R.id.item_integral_show_text2);
      }
   }

   private Pull pull;

   public void setPull(Pull pull) {
      this.pull = pull;
   }

   public interface Pull {
      void pu(int position);
   }
}
