package com.example.lenovo.fubaihui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.bean.Shopping_cart;
import com.example.lenovo.fubaihui.bean.Token;
import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.CommonPresenter;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.RecyclerAdapter_shopping_cart;
import com.example.lenovo.fubaihui.model.TestModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements ICommonView,RecyclerAdapter_shopping_cart.Total,RecyclerAdapter_shopping_cart.Money{


    private RadioGroup radioGroup;
    private RecyclerView recyclerView;
    private ArrayList<Shopping_cart.DataBean.GoodsBean> cartlist=new ArrayList<>();
    private String tokenstring=new String();
    private RelativeLayout relativeLayout;
    private ArrayList<Boolean> booleans=new ArrayList<>();
    private TextView textView;
    private float money=0;
    private TextView textView1;
    private float numnum;
    private TextView exit;
    private RelativeLayout button1;
    private RelativeLayout button2;
    private RecyclerAdapter_shopping_cart recyclerAdapter_shopping_cart;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        radioGroup = view.findViewById(R.id.radg);
        relativeLayout = view.findViewById(R.id.rela);
        recyclerView = view.findViewById(R.id.shop_re);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        textView = view.findViewById(R.id.jin);
        exit = view.findViewById(R.id.edit_text);
        clickRadioButton();
        CommonPresenter mPresenter=new CommonPresenter();
        mPresenter.bind(this, new TestModel());
        mPresenter.getData(ApiConfig.POST_TOKEN,"145","okhgkuejg97DhukoodkjkdjYIidnjkdjiipsteom");
        textView1 = view.findViewById(R.id.select_all);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonPresenter mPresenter=new CommonPresenter();
                mPresenter.bind(ShopFragment.this, new TestModel());
                mPresenter.getData(ApiConfig.POST_TOKEN,"145","okhgkuejg97DhukoodkjkdjYIidnjkdjiipsteom");
                booleans.clear();
                String s = textView1.getText().toString();
                if(s.equals("全选")){
                    numnum=0;
                    for (int i=0;i<cartlist.size();i++){
                        booleans.add(true);
                        String pay_price = cartlist.get(i).getPay_price();
                        float ss= Float.parseFloat(pay_price);
                        int p= Integer.parseInt(cartlist.get(i).getNum());
                        numnum+=(ss*p);
                        Log.e("00000",ss+"|"+p);
                    }
                    textView.setText("￥"+numnum);
                    textView1.setText("全不选");
                }else if (s.equals("全不选")){
                    for (int i=0;i<cartlist.size();i++){
                        booleans.add(false);
                    }
                    numnum=0;
                    textView.setText("￥"+numnum);
                    textView1.setText("全选");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<cartlist.size();i++){
                    if (booleans.get(i)){
                        CommonPresenter mPresenter3=new CommonPresenter();
                        mPresenter3.bind(ShopFragment.this, new TestModel());
                        mPresenter3.getData(ApiConfig.POST_DELETE_SHOPPING_CART,"145",cartlist.get(i).getId(),tokenstring);
                        cartlist.remove(i);
                        booleans.remove(i);
                        recyclerAdapter_shopping_cart.notifyDataSetChanged();
                        textView1.setText("全选");
                    }
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = exit.getText().toString();
                if(s.equals("编辑")){
                    exit.setText("完成");
                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.VISIBLE);
                } else if(s.equals("完成")){
                    exit.setText("编辑");
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    private void clickRadioButton() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rad1:
                        CommonPresenter mPresenter1=new CommonPresenter();
                        mPresenter1.bind(ShopFragment.this, new TestModel());
                        mPresenter1.getData(ApiConfig.POST_SHOW_SHOPPING_CART,"0","145",tokenstring);
                        break;
                    case R.id.rad2:
                        CommonPresenter mPresenter2=new CommonPresenter();
                        mPresenter2.bind(ShopFragment.this, new TestModel());
                        mPresenter2.getData(ApiConfig.POST_SHOW_SHOPPING_CART,"4","145",tokenstring);
                        break;
                    case R.id.rad3:
                        CommonPresenter mPresenter3=new CommonPresenter();
                        mPresenter3.bind(ShopFragment.this, new TestModel());
                        mPresenter3.getData(ApiConfig.POST_SHOW_SHOPPING_CART,"5","145",tokenstring);
                        break;
                }
            }
        });
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi){
            case ApiConfig.POST_SHOW_SHOPPING_CART:
                cartlist.clear();
                Shopping_cart shopping_cart= (Shopping_cart) successResult;
                Log.e("------","购物车数据成功");
                Log.e("---list---",shopping_cart.getCode()+"");
                ArrayList<Shopping_cart.DataBean.GoodsBean> list=new ArrayList<>();
                for (int i=0;i<shopping_cart.getData().size();i++){
                    for (int j=0;j<shopping_cart.getData().get(i).getGoods().size();j++){
                        list.add(shopping_cart.getData().get(i).getGoods().get(j));
                    }
                }
                cartlist.addAll(list);
                show_cart();
                break;
            case ApiConfig.POST_TOKEN:
                Token token= (Token) successResult;
                tokenstring=token.getData();
                Log.e("------",tokenstring);
                cart(tokenstring);
                break;
            case ApiConfig.POST_DELETE_SHOPPING_CART:
                Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();

        }
    }

    private void cart(String s) {
        CommonPresenter mPresenter=new CommonPresenter();
        mPresenter.bind(this, new TestModel());
        mPresenter.getData(ApiConfig.POST_SHOW_SHOPPING_CART,"0","145",tokenstring);
    }

    private void show_cart() {
        if (cartlist.size()>1){
            for (int i=0;i<cartlist.size();i++){
                booleans.add(false);
            }
            recyclerAdapter_shopping_cart = new RecyclerAdapter_shopping_cart(cartlist,booleans,getContext(),tokenstring);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            recyclerView.setAdapter(recyclerAdapter_shopping_cart);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            recyclerAdapter_shopping_cart.setTotal(this);
            recyclerAdapter_shopping_cart.setMoney(this);
        }else {
            Toast.makeText(getContext(),"啊，哦，购物车是空的",Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }

    @Override
    public void pu(int position, boolean b,int num) {
        float pay_price = Float.parseFloat(cartlist.get(position).getPay_price());
        //int num= Integer.parseInt(cartlist.get(position).getNum());
        String s = textView.getText().toString();
        String[] pp = s.split("￥");
        float q= Float.parseFloat(pp[1]);
        if (b){
            textView1.setText("全不选");
            q+=(pay_price*num);
            textView.setText("￥"+q);
        }else {
            textView1.setText("全选");
            q-=(pay_price*num);
            textView.setText("￥"+q);
        }
    }

    @Override
    public void pu1(int i,boolean b, float num) {
        if (b){
            String s = textView.getText().toString();
            String[] pp = s.split("￥");
            float y= Float.parseFloat(pp[1]);
            if (i==0){
                textView.setText("￥"+(y+num));
            }else {
                textView.setText("￥"+(y-num));
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        booleans.clear();
        cartlist.clear();
    }
}
