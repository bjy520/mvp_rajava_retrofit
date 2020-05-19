package com.shuaiyu.netlib.observer;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        Success(t);
    }

    @Override
    public void onError(Throwable e) {
        Error(e);
    }

    @Override
    public void onComplete() {

    }
    public abstract void Success(T u);
    public abstract   void Error(Throwable e);
}
