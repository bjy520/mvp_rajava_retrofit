package com.shuaiyu.mynet;

import android.app.Application;

import com.shuaiyu.mynet.netin.NetStatus;
import com.shuaiyu.netlib.INetWorkReQuiredInfo;
import com.shuaiyu.netlib.NetApi;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetApi.init(new NetStatus());
    }
}
