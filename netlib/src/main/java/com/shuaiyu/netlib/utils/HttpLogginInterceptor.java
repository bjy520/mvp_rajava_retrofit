package com.shuaiyu.netlib.utils;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpLogginInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request  builder=chain.request();

        Log.e("TAG", "请求参数： "+builder.url() );

        return chain. proceed (builder.newBuilder().build());
}
}
