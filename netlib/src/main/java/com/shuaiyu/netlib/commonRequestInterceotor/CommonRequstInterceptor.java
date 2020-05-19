package com.shuaiyu.netlib.commonRequestInterceotor;

import com.shuaiyu.netlib.INetWorkReQuiredInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonRequstInterceptor implements Interceptor {
    private INetWorkReQuiredInfo iNetWorkReQuiredInfo;
    public CommonRequstInterceptor(INetWorkReQuiredInfo iNetWorkReQuiredInfo){
        this.iNetWorkReQuiredInfo=iNetWorkReQuiredInfo;
    }
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder=chain.request().newBuilder();
        builder. addHeader( "os" ," android ") ;
        builder. addHeader( "VersionCode" ,iNetWorkReQuiredInfo.getVersionCode()) ;
        builder. addHeader( "os" ," android ") ;
        return chain. proceed (builder.build());
}
}
