package com.example.lenovo.fubaihui.frame;

import com.example.lenovo.fubaihui.bean.StoreClassifyInfo;
import com.example.lenovo.fubaihui.bean.StoreListInfo;
import com.example.lenovo.fubaihui.bean.StoreParticularsInfo;
import com.example.lenovo.fubaihui.bean.DetailsInfo;
import com.example.lenovo.fubaihui.bean.Integral;
import com.example.lenovo.fubaihui.bean.Integral_show;
import com.example.lenovo.fubaihui.bean.TeamerRankInfo;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;
import com.example.lenovo.fubaihui.bean.Home_Choiceness;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;



public interface INetService {
   //http://bkbapi.dqdgame.com/group/app/topic/list
   @GET("group/app/topic/list")
   Observable<TeamerRankInfo> getTopic();

   @GET("APP/Xinv/sjtojoininfo")
   Observable<Integral> getIntegral();

   @POST("APP/Xtojoin/garage_list")
   Observable<Integral_show> getIntegral_show(@Query("sheng") String sheng, @Query("shi") String
       shi, @Query("xian") String xian);

   @POST("APP/Xtojoin/garage_list")
   Observable<Integral_show> getIntegral_show_pop(@Query("garage_cid") String garage_cid);
   //http://bkbapi.dqdgame.com/group/app/topic/list
  /* @GET("group/app/topic/list")
   Observable<TeamerRankInfo> getTopic();*/

   @POST("APP/Xtojoin/garage_list")
   @FormUrlEncoded
   Observable<FranchiseeInfo> getFranchisee(@Field("type") int type);

   //主页精选
   @POST("APP/Xone/goodslist")
   Observable<Home_Choiceness> getHomeChoiceness();

   //商家详情
   @POST("APP/Xtojoin/mer_details")
   Observable<DetailsInfo> getDetailsInfo(@Body RequestBody  body);

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
