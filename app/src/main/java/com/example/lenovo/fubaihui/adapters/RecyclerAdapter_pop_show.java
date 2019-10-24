package com.example.lenovo.fubaihui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.fubaihui.R;

import java.util.ArrayList;

/**
 * Created by 暗影精灵 on 2019/10/22.
 */

public class RecyclerAdapter_pop_show extends RecyclerView.Adapter<RecyclerAdapter_pop_show.ViewHolder>{
    private ArrayList<String> list;
    private Context context;

    public RecyclerAdapter_pop_show(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter_pop_show.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_pop_show, null);
        ViewHolder viewHolder=new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_pop_show.ViewHolder holder, final int position) {
        holder.textView1.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pult.put(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.pop_text_item);
        }
    }
    private Pult pult;

    public void setPul(Pult pult) {
        this.pult = pult;
    }
    public interface Pult{
        void put(int position);
    }
}
