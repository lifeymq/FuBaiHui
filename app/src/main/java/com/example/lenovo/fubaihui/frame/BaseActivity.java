package com.example.lenovo.fubaihui.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lenovo on 2019/9/20.
 */

public class BaseActivity extends AppCompatActivity {
   public MyApplication mApplication;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   public void showLog(Object content) {
      Log.e("睚眦", content.toString());
   }

   public void showToast(Object content) {
      Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
   }


}
