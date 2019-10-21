package com.example.lenovo.fubaihui.widget;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebSettings;


import com.example.lenovo.fubaihui.frame.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class NetHeaders {
    private static String userAgent;
    static {
        if (TextUtils.isEmpty(userAgent))
            userAgent = getUserAgent();
    }

    public static Map getHeadMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Pragma", "no-cache");
        headers.put("Cache-Control", "no-cache");
//        headers.put("Authorization", Application10.getApplication().mToken);
        headers.put("UUID", MyApplication.getApplication().mUuid.toString());
        headers.put("device_id",MyApplication.getApplication().mUuid.toString());
        headers.put("plat_form","android");
        headers.put("device-tag", "0");
        headers.put("User-Agent", userAgent);
        headers.put("lang", "zh-cn");
        headers.put("action", "auto");
        headers.put("android-channel", "qq");
        headers.put("charset", "UTF-8");
        headers.put("device-tag", "0");
        headers.put("fingerprint-shumei", "20190511180141414050c7af7815a81b11195bb67be624017f1983babeea53");
        return headers;
    }

    public static Map<String, String> signHeader() {
        String path = "/v2/sms/send";
//        String security = SIGN_API_KEY;
        String time = String.valueOf(System.currentTimeMillis() / 1000);
//        String sign = buildSign(path, sUuid, security, SIGN_API_KEY, time);
        Map<String, String> header = new HashMap<>();
//        header.put("api-key", SIGN_API_KEY);
        header.put("time", time);
//        header.put("sign", sign);
        return header;
    }

    /**
     * 拼接签名字符串
     */
    private static String buildSign(String url, String uuid, String security, String apiKey,
                                    String time) {
        String hash = "";
        try {
            String sign = uuid + url + apiKey + time + security;
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiKey.getBytes(), "HmacSHA256");
            mac.init(secret_key);
            hash = Base64.encodeToString(mac.doFinal(sign.getBytes()), Base64.DEFAULT);
            if (TextUtils.isEmpty(hash))
                return hash;
            StringBuffer sb;
            sb = new StringBuffer();
            for (int i = 0; i < hash.length(); i++) {
                int asc = hash.charAt(i);
                if (asc != 10 && asc != 13) {
                    sb.append(hash.subSequence(i, i + 1));
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static String getUserAgent() {
        int versionCode = 1;
        try {
            versionCode = MyApplication.getApplication().getPackageManager().getPackageInfo(MyApplication.getApplication().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException pE) {
            pE.printStackTrace();
        }
        String s = WebSettings.getDefaultUserAgent(MyApplication.getAppContext()) + " News/" + versionCode + " Android/"
                + versionCode + " NewsApp/" + versionCode + " SDK/"
                + Build.VERSION.SDK_INT + " VERSION/"
                + getVersionName();
        return s;
    }

    public static String getVersionName() {
        try {
            PackageManager manager = MyApplication.getAppContext().getPackageManager();
            PackageInfo info = manager
                    .getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown";
    }

    public static int getVersionCode() {
        try {
            PackageManager manager = MyApplication.getAppContext().getPackageManager();
            PackageInfo info = manager
                    .getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
