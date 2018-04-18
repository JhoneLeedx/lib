package com.yxt.itv.library.http.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * @author <font color="pink"><b>JhoneLee</b></font>
 * @Date 2017/11/21
 * @Version 1.0
 * @Description  接口基类
 */
public class BaseEntity<T> {

    @SerializedName("Code")
    private int mCode;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Data")
    private T mData;
    private static int SUCCESS_CODE=200;//成功的code

    public boolean isSuccess(){
        return getmCode()==SUCCESS_CODE;
    }

    public int getmCode() {
        return mCode;
    }

    public void setmCode(int mCode) {
        this.mCode = mCode;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }
}
