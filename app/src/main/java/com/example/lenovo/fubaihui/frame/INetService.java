package com.example.lenovo.fubaihui.frame;

import com.example.lenovo.fubaihui.bean.Integral;
import com.example.lenovo.fubaihui.bean.Integral_show;
import com.example.lenovo.fubaihui.bean.TeamerRankInfo;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;
import com.example.lenovo.fubaihui.bean.Home_Choiceness;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by lenovo on 2019/9/19.
 */

public interface INetService {
    //http://bkbapi.dqdgame.com/group/app/topic/list
    @GET("group/app/topic/list")
    Observable<TeamerRankInfo> getTopic();

    @GET("APP/Xinv/sjtojoininfo")
    Observable<Integral> getIntegral();

    @POST("APP/Xtojoin/garage_list")
    Observable<Integral_show> getIntegral_show(@Query("sheng")String sheng, @Query("shi")String shi, @Query("xian")String xian);

    @POST("APP/Xtojoin/garage_list")
    Observable<Integral_show> getIntegral_show_pop(@Query("garage_cid")String garage_cid);
   //http://bkbapi.dqdgame.com/group/app/topic/list
  /* @GET("group/app/topic/list")
   Observable<TeamerRankInfo> getTopic();*/

   @POST("APP/Xtojoin/garage_list")
   @FormUrlEncoded
   Observable<FranchiseeInfo> getFranchisee(@Field("type") int type);

   //主页精选
   @POST("APP/Xone/goodslist")
   Observable<Home_Choiceness> getHomeChoiceness();





}
