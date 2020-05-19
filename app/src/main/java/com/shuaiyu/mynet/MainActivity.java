package com.shuaiyu.mynet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shuaiyu.mynet.netin.UserInfo;
import com.shuaiyu.mynet.netin.UserInfoService;
import com.shuaiyu.netlib.NetApi;
import com.shuaiyu.netlib.observer.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="TAG" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetApi.getService(UserInfoService.class).getWeather("48000d6102193f1d8da193f5a426ccf4","天蝎","狮子").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    public void Success(UserInfo u) {
                        Log.e(TAG, "Success: "+new Gson().toJson(u));
                    }

                    @Override
                    public void Error(Throwable e) {

                    }
                });
            }
        });
    }
}
