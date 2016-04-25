package com.jinfulin.quick_master.fragment;

/**
 * Created by king on 2015/12/1.
 */

import android.view.View;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jinfulin.quick_master.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jinfulin.quick_master.UI.MainUI;
import com.jinfulin.quick_master.base.BaseFragment;

public class TwoFragment extends BaseFragment {
    @ViewInject(R.id.iv_content)
    TextView iv_content;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.activity_two, null);
        ViewUtils.inject(this, view);// 注入xutils工具 因为getDeclaredFields 只能注入自己类申明的变量
        return view;
    }

    @Override
    public void initData() {
        setBarTitleText("报表");

        // 初始化ViewPager中的数据集合
        iv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void enableSlidingMenu(boolean enable) {
        // 获取MainUI中的SlidingMenu对象
        MainUI mainUI = (MainUI) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            // 设置为不可滑动，不弹出菜单界面
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }


}

