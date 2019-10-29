package com.example.lenovo.fubaihui.model;

import com.example.lenovo.fubaihui.frame.ApiConfig;
import com.example.lenovo.fubaihui.frame.Config;
import com.example.lenovo.fubaihui.frame.ICommonModel;
import com.example.lenovo.fubaihui.frame.ICommonView;
import com.example.lenovo.fubaihui.frame.NetManager;

public class FranchiseeModel implements ICommonModel {

   @Override
   public void getData(final int whichApi, final ICommonView presenterCallBack, Object[] params) {
      NetManager manager = NetManager.getNetManager();
      switch (whichApi){
         case ApiConfig.GET_FRANCHISEE:
            manager.method(manager.getNetService(Config.BASEURL).getFranchisee((int) params[0]),whichApi,presenterCallBack);
            break;
      }
   }

}
