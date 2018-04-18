package com.yxt.itv.library.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yxt.itv.library.ioc.ViewUtils;
import com.yxt.itv.library.permission.PermissionHelper;


/**
 * @author <font color="pink"><b>JhoneLee</b></font>
 * @Date 2017/11/1
 * @Version 1.0
 * @Description 基类activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化界面
        setContentView();
        ViewUtils.inject(this);
        //初始化标题
        initTitle();
        //初始化数据
        initData();
        //初始化view
        initView();
        // AlertDialog
    }

    protected abstract void initData();

    protected abstract void setContentView();

    protected abstract void initTitle();

    protected abstract void initView();

    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    protected void startActivity(Class<?> clazz,Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
