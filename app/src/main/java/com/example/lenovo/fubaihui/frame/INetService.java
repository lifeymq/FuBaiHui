package com.example.lenovo.fubaihui.frame;

import com.example.lenovo.fubaihui.bean.CodeBean;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;
import com.example.lenovo.fubaihui.bean.RegisterBean;
import com.example.lenovo.fubaihui.bean.SignBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by lenovo on 2019/9/19.
 */

public interface INetService {

   @POST("APP/Xtojoin/garage_list")
   @FormUrlEncoded
   Observable<FranchiseeInfo> getFranchisee(@Field("type") int type);

   @POST("APP/user/register")
   @FormUrlEncoded
   Observable<RegisterBean> getRegister(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("recommend_code") Number recommend_code,
                                        @Field("agree") Number agree,
                                        @Field("yzm") Number yzm);

   @POST("APP/Public/sendsms")
   @FormUrlEncoded
   Observable<CodeBean> getCode(@Field("phone") String phone);

   @POST("APP/user/login")
   @FormUrlEncoded
   Observable<SignBean> getSign(@Field("username") String username,
                                @Field("password") String password);
}
