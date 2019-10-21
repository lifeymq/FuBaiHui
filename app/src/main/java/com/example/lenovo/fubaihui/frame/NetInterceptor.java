package com.example.lenovo.fubaihui.frame;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


import com.example.lenovo.fubaihui.safe.AES;
import com.example.lenovo.fubaihui.widget.NetHeaders;
import com.example.lenovo.fubaihui.widget.ParametersInfo;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

import static com.yiyatech.utils.NetworkUtils.isNetworkConnected;


public class NetInterceptor {
   private static volatile NetInterceptor sNetInterceptor;

   private NetInterceptor() {
   }

   public static NetInterceptor getNetInterceptor() {
      if (sNetInterceptor == null) {
         synchronized (NetManager.class) {
            if (sNetInterceptor == null) {
               sNetInterceptor = new NetInterceptor();
            }
         }
      }
      return sNetInterceptor;
   }

   /**
    * addInterceptor 添加拦截器
    * addNetworkInterceptor 添加网络拦截器，缓存拦截器需要在这个方法和addInterceptor这个里边同时添加
    * sslSocketFactory 信任所有ssl证书，相当于跳过证书
    *
    * @return
    */
   public OkHttpClient getClientWithoutCache() {
      return new OkHttpClient.Builder()
          .connectTimeout(20, TimeUnit.SECONDS)
          .readTimeout(20, TimeUnit.SECONDS)
          .writeTimeout(20, TimeUnit.SECONDS)
          .addInterceptor(getLogInterceptor())
          .addInterceptor(new CommonHeadersInterceptor())
          .sslSocketFactory(NetTrustManager.getNetTrustManager().createSSLSocketFactory())
          .hostnameVerifier(new NetTrustManager.TrustAllHostnameVerifier())
          .build();
   }

   public OkHttpClient getClientWithCache() {
      return new OkHttpClient.Builder()
          .cache(new Cache(new File(MyApplication.getApplication().getCacheDir(), "NetCache10"),
              10 * 1024 * 1024))
          .connectTimeout(20, TimeUnit.SECONDS)
          .readTimeout(20, TimeUnit.SECONDS)
          .writeTimeout(20, TimeUnit.SECONDS)
          .addInterceptor(getLogInterceptor())
          .addInterceptor(new CommonHeadersInterceptor())
          .addInterceptor(cacheInterceptor(MyApplication.getAppContext()))
          .addNetworkInterceptor(cacheInterceptor(MyApplication.getAppContext()))
          .sslSocketFactory(NetTrustManager.getNetTrustManager().createSSLSocketFactory())
          .hostnameVerifier(new NetTrustManager.TrustAllHostnameVerifier())
          .build();
   }

   static HttpLoggingInterceptor getLogInterceptor() {
      //设置log拦截器拦截内容
      HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
      //新建log拦截器
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
         @Override
         public void log(String message) {
            Log.e("------retrofit-------", message);
         }
      });
      loggingInterceptor.setLevel(level);
      return loggingInterceptor;
   }

   static class CommonHeadersInterceptor implements Interceptor {
      @SuppressLint("NewApi")
      @Override
      public Response intercept(Chain chain) throws IOException {
         Request request = chain.request()
             .newBuilder()
             .headers(Headers.of(NetHeaders.getHeadMap()))
             .build();
         return chain.proceed(request);
      }
   }

   static class CommonParamsInterceptor implements Interceptor {

      @Override
      public Response intercept(Chain chain) throws IOException {
         Request request = chain.request();
         HttpUrl httpUrl = request.url()
             .newBuilder()
             .scheme(request.url().scheme())
             .host(request.url().host())
             .addQueryParameter("token1", MyApplication.getApplication().mToken)
             .addQueryParameter("token2", "2")
             .build();
         Request newRequest = request.newBuilder()
             .method(request.method(), request.body())
             .url(httpUrl)
             .build();

         return chain.proceed(newRequest);
      }
   }

   static class CommonParamsPostInterceptor implements Interceptor {

      @Override
      public Response intercept(Chain chain) throws IOException {
         FormBody.Builder formBuilder = new FormBody.Builder();
         formBuilder.add("__timestamp", String.valueOf(System.currentTimeMillis()));
         formBuilder.add("deviceToken", new AES().encrypt("ABC"));
         RequestBody commonRequestBody = formBuilder.build();
         Request request = chain.request();
         RequestBody getBody = request.body();
         String getString = bodyToString(getBody);
         String splitJointString = getString.length() > 0 ? getString + "&" + bodyToString
             (commonRequestBody) : bodyToString(commonRequestBody);
         Request newRequest = request.newBuilder().post(RequestBody.create(MediaType.parse
             ("application/x-www-form-urlencoded;charset=UTF-8"), splitJointString)).build();
         return chain.proceed(newRequest);
      }
   }

   static class CommonParamsPostJsonInterceptor implements Interceptor {

      @Override
      public Response intercept(Chain chain) throws IOException {
         ParametersInfo commonBeanInfo = new ParametersInfo(String.valueOf(System
             .currentTimeMillis()), new AES().encrypt("ABC"));
         String commonJson = new Gson().toJson(commonBeanInfo);
         Request request = chain.request();
         RequestBody getBody = request.body();
         String getString = bodyToString(getBody);
         String splitJointString = getString.length() > 0 ? mergeJson(commonJson, getString)
             .toString() : commonJson;
         Request newRequest = request.newBuilder().post(RequestBody.create(MediaType.parse
             ("application/json;charset=UTF-8"), splitJointString)).build();
         return chain.proceed(newRequest);
      }
   }

   public static JSONObject mergeJson(Object commonJson, Object getJson) {
      try {
         JSONObject commonJsonObj = new JSONObject(commonJson.toString());
         JSONObject getJsonObj = new JSONObject(getJson.toString());
         Iterator iterator = getJsonObj.keys();
         while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Object value2 = getJsonObj.get(key);
            if (commonJsonObj.has(key)) {
               Object value1 = commonJsonObj.get(key);
               if (!(value1 instanceof JSONObject)) {
                  commonJsonObj.put(key, value2);
               } else {
                  commonJsonObj.put(key, mergeJson(value1, value2));
               }
            } else {
               commonJsonObj.put(key, value2);
            }
         }
         return commonJsonObj;
      } catch (JSONException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static String bodyToString(final RequestBody request) {
      try {
         final RequestBody copy = request;
         final Buffer buffer = new Buffer();
         if (copy != null)
            copy.writeTo(buffer);
         else
            return "";
         return buffer.readUtf8();
      } catch (final IOException e) {
         return "did not work";
      }
   }

   /**
    * 网络优先数据缓存拦截器
    *
    * @return 拦截器对象
    */
   public static Interceptor cacheInterceptor(final Context context) {
      Interceptor interceptor = new Interceptor() {
         @Override
         public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();//获取请求
            //没有网络的时候强制使用缓存
            if (!isNetworkConnected(context)) {
               request = request.newBuilder()
                   .cacheControl(CacheControl.FORCE_CACHE)
                   .build();
               Log.e("睚眦", "没网读取缓存");
            }
            Response response = chain.proceed(request);
            if (isNetworkConnected(context)) {
               return response.newBuilder()
                   .removeHeader("Pragma")
                   .header("Cache-Control", "public,max-age" + 0)
                   .build();
            } else {
               int maxTime = 4 * 24 * 60 * 30;
               return response.newBuilder()
                   .removeHeader("Pragma")
                   .header("Cache-Control", "public,only-if-cached,max-state=" + maxTime)
                   .build();
            }
         }
      };
      return interceptor;
   }
}
