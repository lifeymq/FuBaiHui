package com.example.lenovo.fubaihui.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author xts
 *         Created by asus on 2019/8/27.
 */

public class ImageLoader {

    public static void setIMage(Context context, String url, ImageView iv){
        Glide.with(context).load(url).into(iv);
    }

    public static void setIMage(Context context, int resId, ImageView iv){
        Glide.with(context).load(resId).into(iv);
    }

    public static void setIMage(Context context, String url, int placeId, ImageView iv){
        RequestOptions options = new RequestOptions()
                .placeholder(placeId);
        Glide.with(context).load(url).apply(options).into(iv);

    }

    public static void setIMage(Context context, int resId, int placeId, ImageView iv){
        RequestOptions options = new RequestOptions()
                .placeholder(placeId);
        Glide.with(context).load(resId).apply(options).into(iv);
    }
}
