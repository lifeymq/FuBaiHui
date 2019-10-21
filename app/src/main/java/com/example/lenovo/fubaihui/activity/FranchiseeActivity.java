package com.example.lenovo.fubaihui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.adapter.FranchiseeAdapter;
import com.example.lenovo.fubaihui.bean.FranchiseeInfo;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.BaseMvpActivity;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.model.FranchiseeModel;
import com.example.lenovo.fubaihui.model.TestModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FranchiseeActivity extends BaseMvpActivity {

   @BindView(R.id.rv_franchisee)
   RecyclerView rvFranchisee;
   private List<FranchiseeInfo.DataBean> dataLists = new ArrayList<>();
   private FranchiseeAdapter franchiseeAdapter;

   @Override
   public ICommonModel setModel() {
      return new FranchiseeModel();
   }

   @Override
   public void initView() {
      super.initView();
      rvFranchisee.setLayoutManager(new LinearLayoutManager(this));
      franchiseeAdapter = new FranchiseeAdapter(dataLists);
      rvFranchisee.setAdapter(franchiseeAdapter);
      franchiseeAdapter.setOnItemClickListener(new FranchiseeAdapter.OnItemClickListener() {
         @Override
         public void onItemClickListener(int position) {
            Intent intent = new Intent(FranchiseeActivity.this,FranchiseeDetailsActivity.class);
            startActivity(intent);
         }
      });
   }

   @Override
   public int getLayoutId() {
      return R.layout.activity_franchisee;
   }

   @Override
   public void setUp() {
      mPresenter.getData(ApiConfig.GET_FRANCHISEE, 0);
   }

   @Override
   public void onSuccess(int whichApi, Object successResult) {
      switch (whichApi) {
         case ApiConfig.GET_FRANCHISEE:
            FranchiseeInfo franchiseeInfo = (FranchiseeInfo) successResult;

               List<FranchiseeInfo.DataBean> data = franchiseeInfo.getData();
               if (data != null && data.size() > 0) {
                  dataLists.addAll(data);
                  franchiseeAdapter.notifyDataSetChanged();
               }

            break;
      }
   }

}
