package com.yxt.itv.library.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by JhoneLee on 2017/10/17.
 *
 * dialog view 辅助处理类
 */

class DialogViewHelper {

    private View mContentView ;

    //防止霸气侧漏  内存泄露
    private SparseArray<WeakReference<View>> mViews ;

    public DialogViewHelper(Context context, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutResId,null);
    }

    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    public  <T extends View> T getView(int viewId){
        WeakReference<View>  viewWeakReference = mViews.get(viewId);
        View view = null;
        if (viewWeakReference!=null){
            view = viewWeakReference.get();
        }
      //  View view = mViews.get(viewId).get();
        if (view == null){
            view = mContentView.findViewById(viewId);
            if (view!=null){
                mViews.put(viewId,new WeakReference<View>(view));
            }
        }
        return (T) view;
    }

    public void setText(int viewId, CharSequence charSequence) {
        //每次都findviewByid 减少findviewbyid次数
        TextView tv = getView(viewId);
        if (tv!=null){
            tv.setText(charSequence);
        }
    }

    public void setOnclickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        if (view!=null){
            view.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置布局
     * @param contentView
     */
    public void setmContentView(View contentView) {
        this.mContentView = contentView;
    }

    //
    public View getContentView() {
        return mContentView;
    }
}
