package com.jinfulin.quick_master.fragment;

/**
 * Created by king on 2015/12/1.
 * 消费开单
 */

import android.view.View;

import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;

public class ThreeFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.activity_three, null);
        ViewUtils.inject(this, view);// xutils注入工具
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData() {
        setBarTitleText("消费开单");


    }




}

