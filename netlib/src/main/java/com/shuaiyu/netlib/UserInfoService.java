package com.shuaiyu.netlib;


import com.shuaiyu.netlib.beans.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meikai on 2020/05/16.
 */
public interface UserInfoService {

    @GET("xzpd/query")
    Observable<UserInfo> getWeather(@Query("key") String key, @Query("men")String men, @Query("women")String women);
}