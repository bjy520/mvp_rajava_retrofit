package com.shuaiyu.netlib;



import com.shuaiyu.netlib.commonRequestInterceotor.CommonRequstInterceptor;
import com.shuaiyu.netlib.commonRequestInterceotor.CommonResponseInterceptor;
import com.shuaiyu.netlib.utils.HttpLogginInterceptor;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetApi {
    String TAG="TAG";
    private static INetWorkReQuiredInfo iNetWorkReQuiredInfo;
    public static String baseUrl="http://apis.juhe.cn/";
    private static HashMap<String,Retrofit> retrofitHashMap=new HashMap<>();
    public static  void init(INetWorkReQuiredInfo netWorkReQuiredInfo){
        iNetWorkReQuiredInfo=netWorkReQuiredInfo;
    }
    public static Retrofit getRetrofit(Class observer){
        if(retrofitHashMap.get(baseUrl+observer.getName())!=null){
            return retrofitHashMap.get(baseUrl+observer.getName());
        }
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl(baseUrl);
        retrofit.client(getOkHttpClient());
        retrofit. addConverterFactory(GsonConverterFactory.create());
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = retrofit.build();
        retrofitHashMap.put(baseUrl+observer.getName(),build);
        return build;
    }
    public static <T> T getService(Class<T> service){

        return getRetrofit(service).create (service) ;
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

    public static <T> ObservableTransformer<T, T> applySchedulers (final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream. subscribeOn (Schedulers. io()).
                        observeOn (AndroidSchedulers. mainThread());
                observable. subscribe (observer) ;
                return observable ;
            }
        };
    }
}
