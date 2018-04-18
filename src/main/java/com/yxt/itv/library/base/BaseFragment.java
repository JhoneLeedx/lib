package com.yxt.itv.library.base;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected View mRootView;
   // protected Unbinder mUnbinder;
    private boolean mIsMulti = false;  //懒加载
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (BaseActivity) context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }
    protected abstract void initData() ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), container,false);
          //  mUnbinder=ButterKnife.bind(this,mRootView);
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }
    protected abstract void initViews();
    protected abstract int getLayoutRes();

    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }
    protected void startActivity(Class<?> clazz,Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            fetchData();
        }
    }
    //mvp网络数据
    protected abstract void fetchData();
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            fetchData();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }
    @Override
    public void onDestroy() {
     //   mUnbinder.unbind();
        super.onDestroy();
    }
}