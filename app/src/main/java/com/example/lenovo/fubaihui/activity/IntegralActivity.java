package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.Integral;
import com.example.lenovo.fubaihui.bean.Integral_show;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.CommonPresenter;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.adapters.RecyclerAdapter_Integral_show;
import com.example.lenovo.fubaihui.adapters.RecyclerAdapter_pop_show;
import com.example.lenovo.fubaihui.model.TestModel;

import java.util.ArrayList;
import java.util.List;

public class IntegralActivity extends AppCompatActivity implements ICommonView,
    RecyclerAdapter_Integral_show.Pull, RecyclerAdapter_pop_show.Pult {


   private TextView provincestext;
   private TextView citytext;
   private TextView countytext;

   private ArrayList<String> title1 = new ArrayList<>();
   private ArrayList<ArrayList<String>> title2 = new ArrayList<>();
   private ArrayList<ArrayList<ArrayList<String>>> title3 = new ArrayList<>();
   private ArrayList<String> poplist = new ArrayList<>();
   private ArrayList<String> popidlist = new ArrayList<>();

   private ArrayList<String> id1 = new ArrayList<>();
   private ArrayList<ArrayList<String>> id2 = new ArrayList<>();
   private ArrayList<ArrayList<ArrayList<String>>> id3 = new ArrayList<>();

   private ArrayList<Integral_show.DataBean> integralshowlist = new ArrayList<>();
   private ImageView imageView;
   private PopupWindow popupWindow;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_lntegral);

      CommonPresenter mPresenter = new CommonPresenter();
      mPresenter.bind(this, new TestModel());
      mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL);
      provincestext = findViewById(R.id.provincestext);
      citytext = findViewById(R.id.citytext);
      countytext = findViewById(R.id.countytext);
      imageView = findViewById(R.id.showpopupwindow_img);
      imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            showpopupwindow();
         }
      });

      // initJsonData();
      findViewById(R.id.provinces).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            showPickerView();
         }
      });
      findViewById(R.id.city).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            showPickerView();
         }
      });
      findViewById(R.id.county).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            showPickerView();
         }
      });

   }

   private void showPickerView() {// 弹出选择器（省市区三级联动）
      OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
         @Override
         public void onOptionsSelect(int options1, int options2, int options3, View v) {
            Log.e("------", id1.get(options1) + "|" + id2.get(options1).get(options2) + id3.get
                (options1).get(options2).get(options3));
            //返回的分别是三个级别的选中位置
            provincestext.setText(title1.get(options1));
            citytext.setText(title2.get(options1).get(options2));
            countytext.setText(title3.get(options1).get(options2).get(options3));
            integralshowlist.clear();
            CommonPresenter mPresenter = new CommonPresenter();
            mPresenter.bind(IntegralActivity.this, new TestModel());
            mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW, id1.get
                (options1), id2.get(options1).get(options2), id3.get(options1).get(options2).get
                (options3));

         }
      })
          .setDividerColor(Color.BLACK)
          .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
          .setContentTextSize(20)
          .build();
      /*  pvOptions.setPicker(title);//一级选择器
        pvOptions.setPicker(title, zhang);//二级选择*/
      if (title1 != null && title1.size() > 0) {
         pvOptions.setPicker(title1, title2, title3);//三级选择器
      } else {
         Toast.makeText(this, "卧槽，没网了", Toast.LENGTH_SHORT).show();
      }
      pvOptions.show();
   }

   @Override
   public void onSuccess(int whichApi, Object successResult) {
      switch (whichApi) {
         case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL:
            Integral info = (Integral) successResult;
            ArrayList<Integral.DataBean.DpflBean> dpflBeans = (ArrayList<Integral.DataBean
                .DpflBean>) info.getData().getDpfl();
            for (int i = 0; i < dpflBeans.size(); i++) {
               poplist.add(dpflBeans.get(i).getName());
               popidlist.add(dpflBeans.get(i).getId());
            }
            ArrayList<Integral> list = new ArrayList<>();
            List<Integral.DataBean.SsxBean> ssx = info.getData().getSsx();
            list.add(info);
            for (int j = 0; j < ssx.size(); j++) {
               Integral.DataBean.SsxBean ssxBean = ssx.get(j);
               ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
               ArrayList<String> CityList_id = new ArrayList<>();//该省的城市id（第二级）
               ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）
               ArrayList<ArrayList<String>> Province_AreaList_id = new ArrayList<>();
               //该省的所有地区id（第三级）
               title1.add(ssx.get(j).getRegion_name());
               id1.add(ssx.get(j).getRegion_id());
               for (int i = 0; i < ssxBean.getShi().size(); i++) {
                  Integral.DataBean.SsxBean.ShiBean shiBean = ssxBean.getShi().get(i);
                  CityList.add(ssx.get(j).getShi().get(i).getRegion_name());
                  CityList_id.add(ssx.get(j).getShi().get(i).getRegion_id());
                  ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                  ArrayList<String> City_AreaList_id = new ArrayList<>();//该城市的所有地区id
                  if (shiBean.getXian() != null && shiBean.getXian().size() > 0) {
                     for (int k = 0; k < shiBean.getXian().size(); k++) {
                        Integral.DataBean.SsxBean.ShiBean.XianBean xianBean = shiBean.getXian()
                            .get(k);
                        City_AreaList.add(ssx.get(j).getShi().get(i).getXian().get(k)
                            .getRegion_name());
                        City_AreaList_id.add(ssx.get(j).getShi().get(i).getXian().get(k)
                            .getRegion_id());
                     }
                  } else {
                     City_AreaList.add("");
                     City_AreaList_id.add("");
                  }
                  Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                  Province_AreaList_id.add(City_AreaList_id);//添加该省所有地区id
               }
               title2.add(CityList);
               id2.add(CityList_id);
               title3.add(Province_AreaList);
               id3.add(Province_AreaList_id);
            }
            break;
         case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW:
            Log.e("------", "银积分数据加载成功");
            Integral_show integral_show = (Integral_show) successResult;
            ArrayList<Integral_show> integral_shows = new ArrayList<>();
            integral_shows.add(integral_show);
            for (int i = 0; i < integral_shows.size(); i++) {
               integralshowlist.addAll(integral_shows.get(i).getData());
            }
            show();
            break;
         case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW_POP:
            Log.e("------", "银积分数据加载成功");
            Integral_show integral_show1 = (Integral_show) successResult;
            ArrayList<Integral_show> integral_shows1 = new ArrayList<>();
            integral_shows1.add(integral_show1);
            for (int i = 0; i < integral_shows1.size(); i++) {
               integralshowlist.addAll(integral_shows1.get(i).getData());
            }
            show();
            break;
      }
   }

   @Override
   public void onFailed(int whichApi, Throwable failedResult) {
      switch (whichApi) {
         case ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL:
            Log.e("------", "地址加载失败");
            break;
      }
   }

   private void showpopupwindow() {
      popupWindow = new PopupWindow(this);
      View from = LayoutInflater.from(this).inflate(R.layout.showpopupwindow, null);
      popupWindow.setOutsideTouchable(true);
      popupWindow.setContentView(from);
      popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
      popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
      popupWindow.showAsDropDown(imageView);
      RecyclerView recyclerView = from.findViewById(R.id.rv_pop);
      RecyclerAdapter_pop_show recyclerAdapter_pop_show = new RecyclerAdapter_pop_show(poplist,
          this);
      LinearLayoutManager layoutManager = new LinearLayoutManager(this);
      recyclerView.setAdapter(recyclerAdapter_pop_show);
      recyclerView.setLayoutManager(layoutManager);
      recyclerAdapter_pop_show.setPul(this);

   }

   private void show() {
      RecyclerView recyclerView = findViewById(R.id.rv_integral_show);
      RecyclerAdapter_Integral_show recyclerAdapter_integral_show = new
          RecyclerAdapter_Integral_show(integralshowlist);
      LinearLayoutManager layoutManager = new LinearLayoutManager(this);
      recyclerView.setAdapter(recyclerAdapter_integral_show);
      recyclerView.setLayoutManager(layoutManager);
      recyclerAdapter_integral_show.setPull(this);
   }

   @Override
   public void pu(int position) {
      Intent intent = new Intent(IntegralActivity.this, IntegralDetailsActivity.class);
      Integral_show.DataBean dataBean = integralshowlist.get(position);
      Bundle bundle = new Bundle();
      bundle.putSerializable("data", dataBean);
      intent.putExtras(bundle);
      startActivity(intent);
   }

   @Override
   public void put(int position) {
      popupWindow.dismiss();
      Log.e("-----", popidlist.get(position) + poplist.get(position));
      integralshowlist.clear();
      CommonPresenter mPresenter = new CommonPresenter();
      mPresenter.bind(this, new TestModel());
      mPresenter.getData(ApiConfig.GET_PERSON_RANKING_RESULT_INTEGRAL_SHOW_POP, popidlist.get
          (position));
   }
}
