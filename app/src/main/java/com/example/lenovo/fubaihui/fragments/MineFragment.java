package com.example.lenovo.fubaihui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.mainactivity.AddressActivity;
import com.example.lenovo.fubaihui.mainactivity.CollectionActivity;
import com.example.lenovo.fubaihui.mainactivity.DataActivity;
import com.example.lenovo.fubaihui.mainactivity.DiscountActivity;
import com.example.lenovo.fubaihui.mainactivity.FriendActivity;
import com.example.lenovo.fubaihui.mainactivity.OrderActivity;
import com.example.lenovo.fubaihui.mainactivity.PhoneActivity;
import com.example.lenovo.fubaihui.mainactivity.RecommendActivity;
import com.example.lenovo.fubaihui.mainactivity.RecommendNumActivity;
import com.example.lenovo.fubaihui.mainactivity.SettingActivity;
import com.example.lenovo.fubaihui.mainactivity.SettledinActivity;
import com.example.lenovo.fubaihui.mainactivity.WalletActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
   @BindView(R.id.iv_mine_image)
   ImageView ivMineImage;
   @BindView(R.id.tv_mine_name)
   TextView tvMineName;
   @BindView(R.id.tv_mine_account)
   TextView tvMineAccount;
   @BindView(R.id.ll_mine_data)
   LinearLayout llMineData;
   @BindView(R.id.ll_mine_discount)
   LinearLayout llMineDiscount;
   @BindView(R.id.ll_mine_setting)
   LinearLayout llMineSetting;
   @BindView(R.id.ll_mine_recommend)
   LinearLayout llMineRecommend;
   @BindView(R.id.ll_mine_recommend_num)
   LinearLayout llMineRecommendNum;
   @BindView(R.id.ll_mine_address)
   LinearLayout llMineAddress;
   @BindView(R.id.ll_mine_settled_in)
   LinearLayout llMineSettledIn;
   @BindView(R.id.ll_mine_login)
   LinearLayout llMineLogin;
   @BindView(R.id.ll_mine_phone)
   LinearLayout llMinePhone;
   @BindView(R.id.ll_mine_friend)
   TextView llMineFriend;
   @BindView(R.id.ll_mine_collection)
   TextView llMineCollection;
   @BindView(R.id.ll_mine_wallet)
   TextView llMineWallet;
   @BindView(R.id.ll_mine_order)
   TextView llMineOrder;
   Unbinder unbinder;

   public MineFragment() {
      // Required empty public constructor
   }


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_mine, container, false);
      unbinder = ButterKnife.bind(this, view);
      return view;
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      initView();
   }

   private void initView() {
      Glide.with(getActivity()).load(R.drawable.ic_fubaihui)
          .circleCrop()
          .into(ivMineImage);

   }

   @Override
   public void onDestroyView() {
      super.onDestroyView();
      unbinder.unbind();
   }

   @OnClick({R.id.ll_mine_data, R.id
       .ll_mine_discount, R.id.ll_mine_setting, R.id.ll_mine_recommend, R.id
       .ll_mine_recommend_num, R.id.ll_mine_address, R.id.ll_mine_settled_in, R.id
       .ll_mine_login, R.id.ll_mine_phone, R.id.ll_mine_friend, R.id.ll_mine_collection, R.id
       .ll_mine_wallet, R.id.ll_mine_order})
   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.ll_mine_data:
            startActivity(new Intent(getActivity(),DataActivity.class));
            break;
         case R.id.ll_mine_discount:
            startActivity(new Intent(getActivity(),DiscountActivity.class));
            break;
         case R.id.ll_mine_setting:
            startActivity(new Intent(getActivity(),SettingActivity.class));
            break;
         case R.id.ll_mine_recommend:
            startActivity(new Intent(getActivity(),RecommendActivity.class));
            break;
         case R.id.ll_mine_recommend_num:
            startActivity(new Intent(getActivity(),RecommendNumActivity.class));
            break;
         case R.id.ll_mine_address:
            startActivity(new Intent(getActivity(),AddressActivity.class));
            break;
         case R.id.ll_mine_settled_in:
            startActivity(new Intent(getActivity(),SettledinActivity.class));
            break;
         case R.id.ll_mine_login:
            //登录
            break;
         case R.id.ll_mine_phone:
            startActivity(new Intent(getActivity(),PhoneActivity.class));
            break;
         case R.id.ll_mine_friend:
            startActivity(new Intent(getActivity(),FriendActivity.class));
            break;
         case R.id.ll_mine_collection:
            startActivity(new Intent(getActivity(),CollectionActivity.class));
            break;
         case R.id.ll_mine_wallet:
            startActivity(new Intent(getActivity(),WalletActivity.class));
            break;
         case R.id.ll_mine_order:
            startActivity(new Intent(getActivity(),OrderActivity.class));
            break;
      }
   }
}
