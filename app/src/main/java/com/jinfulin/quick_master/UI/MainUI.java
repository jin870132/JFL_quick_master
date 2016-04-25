package com.jinfulin.quick_master.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.base.BaseFragment;
import com.jinfulin.quick_master.factory.FragmentFactory;
import com.jinfulin.quick_master.fragment.LeftFragment;
import com.jinfulin.quick_master.fragment.OneFragment;
import com.jinfulin.quick_master.util.CustomToast;
import com.jinfulin.quick_master.util.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainUI extends SlidingFragmentActivity implements View.OnClickListener {
    @ViewInject(R.id.iv_radio_1)
    private ImageView iv_radio_1;
    @ViewInject(R.id.iv_radio_2)
    private ImageView iv_radio_2;
    @ViewInject(R.id.iv_radio_3)
    private ImageView iv_radio_3;
    @ViewInject(R.id.iv_radio_4)
    private ImageView iv_radio_4;
    @ViewInject(R.id.iv_radio_0)
    private ImageView iv_radio_0;
    @ViewInject(R.id.radio_1)
    private ImageView radio_1;
    @ViewInject(R.id.radio_2)
    private ImageView radio_2;
    @ViewInject(R.id.radio_3)
    private ImageView radio_3;
    @ViewInject(R.id.radio_4)
    private ImageView radio_4;
    @ViewInject(R.id.radio_0)
    private ImageView radio_0;

    private static final String TAG_LEFT_FRAGMENT = "tag_left_fragment";
    private static final String TAG_CONTENT_FRAGMENT = "tag_content_fragment";
    public SlidingMenu slidingMenu;
    private FragmentManager fragmentManager;
    private long time = 0;
    private int currentPositon=0;

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, MainUI.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
        // 设置正文
        setContentView(R.layout.activity_main_ui);
        // 设置左边菜单
        setBehindContentView(R.layout.activity_left);
        ViewUtils.inject(this);
        slidingMenu = getSlidingMenu();
        // 设置滑动模式 只运行左边可以滑动
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸范围 全屏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置主界面存留的宽度
        slidingMenu.setBehindOffset(UIUtils.dip2px(150));
        init();
        iv_radio_1.setOnClickListener(this);
        iv_radio_2.setOnClickListener(this);
        iv_radio_3.setOnClickListener(this);
        iv_radio_4.setOnClickListener(this);
        iv_radio_0.setOnClickListener(this);

    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 对左侧菜单 添加内容 内容是Fragment中的onCreateView 返回的view
        transaction.replace(R.id.fl_left, new LeftFragment(), TAG_LEFT_FRAGMENT);
        // 对正文界面 添加内容 内容是Fragment中的onCreateView 返回的view
        transaction.replace(R.id.fl_content, new OneFragment(), TAG_CONTENT_FRAGMENT);
        transaction.commit();
    }

    /**
     * 对外提供获取左侧菜单的方法
     *
     * @return
     */
    public LeftFragment getLeftFragment() {
        return (LeftFragment) fragmentManager.findFragmentByTag(TAG_LEFT_FRAGMENT);
    }

    /**
     * 对外提供获取正文界面的方法
     *
     * @return
     */
    public OneFragment getContentFragment() {
        return (OneFragment) fragmentManager.findFragmentByTag(TAG_CONTENT_FRAGMENT);
    }

    /**
     * 对外提供获取正文界面的方法
     *
     * @return
     */
    public void setContentFragment(int position) {
        slidingMenu.toggle();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 对正文界面 添加内容 内容是Fragment中的onCreateView 返回的view
        transaction.replace(R.id.fl_content, FragmentFactory.createFragment(position), TAG_CONTENT_FRAGMENT);
        transaction.commit();
    }

    public void switchContent(BaseFragment from, BaseFragment to) {
        if (from == null || to == null)
            return;
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(from).add(R.id.fl_content, to).commit();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit();
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - time < 2000) {// 点击2次的时间小于2秒，退出程序
                UIUtils.finishAll();
            } else {
                time = System.currentTimeMillis();
                CustomToast.showToast("再点一次退出程序！");
            }
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtils.removeActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_radio_1:
                switchContent(FragmentFactory.createFragment(currentPositon), FragmentFactory.createFragment(1));
                setPicChage(1);
                break;
            case R.id.iv_radio_2:
                switchContent(FragmentFactory.createFragment(currentPositon), FragmentFactory.createFragment(2));
                setPicChage(2);
                break;
            case R.id.iv_radio_3:
                setPicChage(3);
                break;
            case R.id.iv_radio_4:
                setPicChage(4);
                break;
            case R.id.iv_radio_0:
                switchContent(FragmentFactory.createFragment(currentPositon), FragmentFactory.createFragment(0));
                setPicChage(0);
                break;

            default:
                setPicChage(0);
                break;
        }
    }

    public void setPicChage(int position) {
        currentPositon = position;
        switch (position) {
            case 1:
                radio_1.setImageResource(R.drawable.tab_1_selected);
                radio_2.setImageResource(R.drawable.tab_2);
                radio_3.setImageResource(R.drawable.tab_3);
                radio_4.setImageResource(R.drawable.tab_4);
                radio_0.setImageResource(R.drawable.tab_0);
                break;
            case 2:
                radio_1.setImageResource(R.drawable.tab_1);
                radio_2.setImageResource(R.drawable.tab_2_selected);
                radio_3.setImageResource(R.drawable.tab_3);
                radio_4.setImageResource(R.drawable.tab_4);
                radio_0.setImageResource(R.drawable.tab_0);

                break;
            case 3:
                radio_1.setImageResource(R.drawable.tab_1);
                radio_2.setImageResource(R.drawable.tab_2);
                radio_3.setImageResource(R.drawable.tab_3_selected);
                radio_4.setImageResource(R.drawable.tab_4);
                radio_0.setImageResource(R.drawable.tab_0);
                break;

            case 4:
                radio_1.setImageResource(R.drawable.tab_1);
                radio_2.setImageResource(R.drawable.tab_2);
                radio_3.setImageResource(R.drawable.tab_3);
                radio_4.setImageResource(R.drawable.tab_4_selected);
                radio_0.setImageResource(R.drawable.tab_0);
                break;
            case 0:
                radio_1.setImageResource(R.drawable.tab_1);
                radio_2.setImageResource(R.drawable.tab_2);
                radio_3.setImageResource(R.drawable.tab_3);
                radio_4.setImageResource(R.drawable.tab_4);
                radio_0.setImageResource(R.drawable.tab_0_selected);
                break;
            default:
                break;
        }
    }
}
