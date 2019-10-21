package com.example.lenovo.fubaihui.safe;

/**
 * Created by 任小龙 on 2019/7/8.
 */
public class Demo {
   /* UEL：http://hsp2.wx.pangjiachen.com/miapp/APPYF.A0000./gateway
    __timestamp：当前时间（加密）
    deviceType：设备标识（1-ios 2-Android）（加密）
    deviceToken：设备识别码（加密）
    currenVersion：当前版本（加密）
    userIdType：（加密）
    buzType：（可不传，后台已加）
    __para：
    __sign：
    __openid：(身份证号）
    state：（APP传app，微信传wechat）
    devtoken：百度token*/
  /* formbodyBuilder.add("devtoken", Application10.);
        formbodyBuilder.add("state", "app");
        formbodyBuilder.add("__openid", BaseApp.getInstance().getUserId());//无加密-maybe  BaseApp.getInstance().aes.encrypt(userid)
        formbodyBuilder.add("__timestamp", timesign);
        formbodyBuilder.add("deviceType", BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getDeviceType()));
        formbodyBuilder.add("deviceToken", BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getDeviceToken()));
        formbodyBuilder.add("currenVersion", BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getCurrenVersion()));
        formbodyBuilder.add("buztype", BaseApp.getInstance().aes.encrypt("")); //buztype
        formbodyBuilder.add("userIdType", BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getUserIdType()));
        formbodyBuilder.add("__para", BaseApp.getInstance().aes.encrypt("__timestamp,deviceType,deviceToken,currenVersion,userIdType,buztype"));
    String rsa = RSASignature.sign(EncryptionByMD5.getMD5(getSignValue("").getBytes()), RSASignature.RSA_PRIVATE);
        formbodyBuilder.add("__sign", rsa);
    RequestBody formbody = formbodyBuilder.build();
    String postBodyString = bodyToString(request.body());
    postBodyString += (postBodyString.length() > 0 ? "&" : "") + bodyToString(formbody);
        Log.i("公共参---->", "postBodyString--->=" + postBodyString);
        requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
//        requestBuilder.post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), postBodyString));
    request = requestBuilder.build();
   private String getSignValue(String buztype) {
       return "__timestamp=" + timesign
               + "&deviceType=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getDeviceType())
               + "&deviceToken=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getDeviceToken())
               + "&currenVersion=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getCurrenVersion())
               + "&userIdType=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getUserIdType())
               + "&buztype=" + BaseApp.getInstance().aes.encrypt(buztype);
   }*/
}
