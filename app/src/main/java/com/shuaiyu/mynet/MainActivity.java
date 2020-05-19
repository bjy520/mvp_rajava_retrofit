package com.shuaiyu.mynet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.shuaiyu.netlib.NetApi;
import com.shuaiyu.netlib.beans.UserInfo;
import com.shuaiyu.netlib.observer.BaseObserver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetApi.get(new BaseObserver<UserInfo>() {
                    @Override
                    public void Success(UserInfo u) {
                        Log.e("TAG", "Success: "+new Gson().toJson(u));
                    }

                    @Override
                    public void Error(Throwable e) {

                    }
                });
            }
        });
    }
}
