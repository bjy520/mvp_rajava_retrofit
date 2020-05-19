package com.shuaiyu.netlib;


import android.util.Log;

import com.google.gson.Gson;
import com.shuaiyu.netlib.beans.UserInfo;
import com.shuaiyu.netlib.commonRequestInterceotor.CommonRequstInterceptor;
import com.shuaiyu.netlib.commonRequestInterceotor.CommonResponseInterceptor;
import com.shuaiyu.netlib.utils.HttpLogginInterceptor;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetApi {
    String TAG="TAG";
    private static INetWorkReQuiredInfo iNetWorkReQuiredInfo;
    public static String baseUrl="http://apis.juhe.cn/";
    public static  void init(INetWorkReQuiredInfo netWorkReQuiredInfo){
        iNetWorkReQuiredInfo=netWorkReQuiredInfo;
    }
    public static void get(Observer observer){
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl(baseUrl);
        retrofit.client(getOkHttpClient());
        retrofit. addConverterFactory(GsonConverterFactory.create());
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofit.build();

        UserInfoService userInfoService = retrofit.build().create(UserInfoService.class);
        userInfoService.getWeather("48000d6102193f1d8da193f5a426ccf4","白羊","金牛").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new CommonRequstInterceptor(iNetWorkReQuiredInfo));
        builder.addInterceptor(new CommonResponseInterceptor());
        if(iNetWorkReQuiredInfo!=null&&iNetWorkReQuiredInfo.isDebug()){
            HttpLogginInterceptor httpLogginInterceptor=new HttpLogginInterceptor();
            builder.addInterceptor(httpLogginInterceptor);
        }
        return builder.build();
    }


}
