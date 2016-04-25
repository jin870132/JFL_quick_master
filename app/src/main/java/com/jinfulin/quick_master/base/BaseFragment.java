package com.jinfulin.quick_master.base;

/**
 * Created by king on 2015/12/1.
 */

import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.UI.MainUI;

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;

    /**
     * 获取Activity的引用
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();// 得到的就是MainUI
        initTitle();
    }

    /**
     * 返回具体的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 子类初始化数据
     */
    public void initData() {

    }

    /**
     * @return 让子类实现 创建自己的view
     */
    public abstract View initView();


    //------------设置title信息---------------------
    public void initTitle() {
        getImageLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainUI)mActivity).getSlidingMenu().toggle();
            }
        });
        setImageLeft(R.mipmap.icon_function);
    }
    /**
     * 隐藏左侧按钮
     */
    public void hideImageLeft(boolean bSetHide) {
        ImageView bt = getImageLeft();
        if (null != bt) {
            if (bSetHide) bt.setVisibility(View.INVISIBLE);
            else bt.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 隐藏右侧按钮
     */
    public void hideImageRight(boolean bSetHide) {
        ImageView bt = getImageRight();
        if (null != bt) {
            if (bSetHide) bt.setVisibility(View.INVISIBLE);
            else bt.setVisibility(View.VISIBLE);
        }
    }
    public ImageView getImageLeft() {
        return (ImageView) mActivity.findViewById(R.id.iv_left);
    }
    public ImageView getImageRight() {
        return (ImageView) mActivity.findViewById(R.id.iv_right);
    }
    public TextView getTextRight() {
        return (TextView) mActivity.findViewById(R.id.tv_right);
    }
    public void  setImageLeft(int drawableId) {
        ImageView bt = getImageLeft();
        if (null != bt) {
            bt.setBackgroundResource(drawableId);
        }
    }
    public void  setImageRight(int drawableId) {
        ImageView bt = getImageRight();
        if (null != bt) {
            bt.setBackgroundResource(drawableId);
        }
    }
    public boolean setBarTitleText(String titleText) {
        TextView tv_title = (TextView) mActivity.findViewById(R.id.tv_title);
        if (null != tv_title) {
            tv_title.setText(titleText);
            return true;
        }
        return false;
    }
}

