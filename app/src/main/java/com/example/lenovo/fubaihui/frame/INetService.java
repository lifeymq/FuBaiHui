package com.example.lenovo.fubaihui.frame;

import com.example.lenovo.fubaihui.bean.StoreClassifyInfo;
import com.example.lenovo.fubaihui.bean.StoreListInfo;
import com.example.lenovo.fubaihui.bean.StoreParticularsInfo;
import com.example.lenovo.fubaihui.bean.TeamerRankInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/9/19.
 */

public interface INetService {
    //http://bkbapi.dqdgame.com/group/app/topic/list
//    @GET("group/app/topic/list")
//    Observable<TeamerRankInfo> getTopic();
//    http://newwasj.zhangtongdongli.com
//    福百惠商城分类列表
    @GET("APP/public/category_list")
    Observable<StoreClassifyInfo> getStoreClassify();

    @GET("APP/Shop/index/cid/1")
    Observable<StoreListInfo> getStoreList(@Query("cid") int cid);

    @GET("APP/Shop/goods_detail/id/1561")
    Observable<StoreParticularsInfo> getStoreParticulars(@Query("id") int id);
}
