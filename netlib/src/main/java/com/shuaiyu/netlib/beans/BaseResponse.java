package com.shuaiyu.netlib.beans;

/**
 * Created by meikai on 2020/05/16.
 */
public class BaseResponse<T> {

    public String resultCode;
    public String reason;
    public T result;
    public int error_code;

}
