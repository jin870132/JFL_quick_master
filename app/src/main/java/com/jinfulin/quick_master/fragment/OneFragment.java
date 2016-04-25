package com.jinfulin.quick_master.fragment;

/**
 * 预约管理
 * Created by king on 2015/12/1.
 */

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.base.BaseFragment;
import com.jinfulin.quick_master.beans.AppointInfo;
import com.jinfulin.quick_master.util.LoadingUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class OneFragment extends BaseFragment {
    @ViewInject(R.id.lv_appoint)
    ListView lv_appoint;
    @ViewInject(R.id.tv_nowDate)
    TextView tv_nowDate;
    @ViewInject(R.id.tv_preday)
    TextView tv_preday;
    @ViewInject(R.id.tv_nextday)
    TextView tv_nextday;
    private ArrayList<AppointInfo> appointInfoList;
    public int daynum = 0;
    private LoadingUtils loadingUtils;

    @Override
    public View initView() {
        loadingUtils = new LoadingUtils(mActivity) {
            @Override
            public void right_click() {

            }
        };
        View view = View.inflate(mActivity, R.layout.activity_appoint, null);
        ViewUtils.inject(this, view);// 注入xutils工具
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void initData() {
        setBarTitleText("预约管理");
    }





}

