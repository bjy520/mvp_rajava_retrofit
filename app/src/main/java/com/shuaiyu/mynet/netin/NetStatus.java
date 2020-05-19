package com.shuaiyu.mynet.netin;

import com.shuaiyu.netlib.BuildConfig;
import com.shuaiyu.netlib.INetWorkReQuiredInfo;

public class NetStatus implements INetWorkReQuiredInfo {
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getVersionCode() {
        return ""+BuildConfig.VERSION_CODE;
    }
}
