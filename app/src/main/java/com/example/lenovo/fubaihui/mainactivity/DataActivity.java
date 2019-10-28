package com.example.lenovo.fubaihui.mainactivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.activity.MainActivity;
import com.example.lenovo.fubaihui.activity.SignActivity;
import com.example.lenovo.fubaihui.dataactivity.AboutActivity;
import com.example.lenovo.fubaihui.dataactivity.AdministrationActivity;
import com.example.lenovo.fubaihui.dataactivity.NameActivity;
import com.example.lenovo.fubaihui.dataactivity.NumberActivity;
import com.example.lenovo.fubaihui.dataactivity.OpinionActivity;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.TestModel;
import com.example.lenovo.fubaihui.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.umeng.socialize.net.dplus.CommonNetImpl.UID;

public class DataActivity extends BaseMvpActivity {


    @BindView(R.id.item_img)
    ImageView itemImg;
    @BindView(R.id.hand_img)
    ImageView handImg;
    @BindView(R.id.data_hand)
    RelativeLayout dataHand;
    @BindView(R.id.number_data)
    TextView numberData;
    @BindView(R.id.data_number)
    RelativeLayout dataNumber;
    @BindView(R.id.data_jian2)
    ImageView dataJian2;
    @BindView(R.id.name_data)
    TextView nameData;
    @BindView(R.id.data_name)
    RelativeLayout dataName;
    @BindView(R.id.data_administration)
    RelativeLayout dataAdministration;
    @BindView(R.id.data_cache)
    RelativeLayout dataCache;
    @BindView(R.id.data_opinion)
    RelativeLayout dataOpinion;
    @BindView(R.id.data_about)
    RelativeLayout dataAbout;
    @BindView(R.id.data_btn)
    Button dataBtn;
    @BindView(R.id.item_toolname)
    TextView itemToolname;
    @BindView(R.id.item_tool)
    Toolbar itemTool;
    private PopupWindow popupWindow;
    public static final String SP_TYPE ="sp_type";
    public static final String USER_TYPE ="user_type";

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data;
    }

    @Override
    public void initView() {
        itemTool.setTitle("");
        itemToolname.setText("个人资料");
        setSupportActionBar(itemTool);
        initPermission();
    }

    @Override
    public void setUp() {

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @OnClick({R.id.item_img, R.id.data_hand, R.id.data_number, R.id.data_jian2, R.id.data_name, R.id.data_administration, R.id.data_cache, R.id.data_opinion, R.id.data_about, R.id.data_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_img: //返回
                finish();
                break;
            case R.id.data_hand: //头像条目
                popupwindows();
                break;
            case R.id.data_number: //账号条目
                Intent number = new Intent(DataActivity.this, NumberActivity.class);
                startActivity(number);
                break;
            case R.id.data_name://昵称条目
                Intent name = new Intent(DataActivity.this, NameActivity.class);
                startActivity(name);
                break;
            case R.id.data_administration://密码管理条目
                Intent administration = new Intent(DataActivity.this, AdministrationActivity.class);
                startActivity(administration);
                break;
            case R.id.data_cache://清除缓存条目
                showToast("缓存清除完毕！");
                break;
            case R.id.data_opinion://意见反馈条目
                Intent opinion = new Intent(DataActivity.this, OpinionActivity.class);
                startActivity(opinion);
                break;
            case R.id.data_about://关于我们条目
                Intent about = new Intent(DataActivity.this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.data_btn://退出登录按钮
                /*Intent btn = new Intent(DataActivity.this,SignActivity.class);
                startActivity(btn);
                finish();*/
                Boolean type = (Boolean) SpUtil.getParam(SP_TYPE, false);
                if (type) {
                    Integer uid = (Integer) SpUtil.getParam(UID, 0);
                    Integer user_type = (Integer) SpUtil.getParam(USER_TYPE, 0);
                    Intent intent = new Intent(DataActivity.this,SignActivity.class);
                    intent.putExtra(UID , uid+"");
                    intent.putExtra(USER_TYPE , user_type+"");
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    private void initPermission() {
        String[] mPermissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        //先判断版本号是否在23（6.0）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查自身的权限是否设置
            int i = ContextCompat.checkSelfPermission(this, mPermissions[0]);
            if (i != PackageManager.PERMISSION_GRANTED) {//没有授权
                //请求授权
                ActivityCompat.requestPermissions(this, mPermissions, 1);
            }
        }
        //先判断版本号是否在23（6.0）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查自身的权限是否设置
            int i = ContextCompat.checkSelfPermission(this, mPermissions[1]);
            if (i != PackageManager.PERMISSION_GRANTED) {//没有授权
                //请求授权
                ActivityCompat.requestPermissions(this, mPermissions, 2);
            }
        }
    }

    private void popupwindows() {
        View view = View.inflate(DataActivity.this, R.layout.pooup_bottom_view, null);
        Button cancelBtn = view.findViewById(R.id.cancelBtn);//取消
        TextView takePhotoBtn = view.findViewById(R.id.takePhotoBtn);//拍照
        TextView PhotoAlbumBtn = view.findViewById(R.id.PhotoAlbumBtn);//相册
        //创建popupWindow对象
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //设置阴影
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(params);
        //设置textView可编辑
        popupWindow.setFocusable(true);
        //设置动画效果
        //设置阴影消失
        //popupWindow.setAnimationStyle(R.style.PopupBottomStyle);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1.0f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(attributes);
            }
        });
        //点击空白消失
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        //展示
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //拍照
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                //9.打开摄像头拍照:
                // 打开拍照程序
                //调出拍照功能
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        //相册
        PhotoAlbumBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri data1 = data.getData();
            handImg.setImageURI(data1);
            EventBus.getDefault().post(data1.toString());
            SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = user.edit();
            edit.putBoolean("isBoolean", true);
            edit.putString("path", data1.toString());
            edit.commit();
        } else if (requestCode == 100 && resultCode == RESULT_OK) {
            Bitmap data2 = data.getParcelableExtra("data");
            Glide.with(this).load(data2).into(handImg);
            SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = user.edit();
            edit.putBoolean("isBoolean", true);
            edit.putString("path", data2.toString());
            edit.commit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        String path = user.getString("path", null);
        Boolean isBoolean = user.getBoolean("isBoolean", false);
        if (isBoolean == true) {
            Glide.with(this).load(Uri.parse(path)).into(handImg);
        }
    }
}
