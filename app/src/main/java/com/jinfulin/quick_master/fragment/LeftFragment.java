package com.jinfulin.quick_master.fragment;

/**
 * Created by king on 2015/12/1.
 */

import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.UI.MainUI;
import com.jinfulin.quick_master.base.BaseFragment;
import com.jinfulin.quick_master.factory.FragmentFactory;
import com.jinfulin.quick_master.util.AccountManager;
import com.jinfulin.quick_master.util.DialogUtils;
import com.jinfulin.quick_master.util.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class LeftFragment extends BaseFragment implements View.OnClickListener {

    private ListView listView;
    private List<MenuItem> menuDataList;// listview的数据
    private int currentClickMenuItem = 0;// 记录点击的某一个Item 的角标
    private TextView tv_appoint;
    private TextView tv_reportform;
    private TextView tv_consumer;
    private TextView tv_modifypwd;
    private TextView tv_logout;
    private ArrayList<TextView> viewList;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.activity_left_main, null);
        tv_appoint = (TextView)view.findViewById(R.id.tv_appoint);
        tv_reportform = (TextView)view.findViewById(R.id.tv_reportform);
        tv_consumer = (TextView)view.findViewById(R.id.tv_consumer);
        tv_modifypwd = (TextView)view.findViewById(R.id.tv_modifypwd);
        tv_logout = (TextView)view.findViewById(R.id.tv_logout);
        viewList = new ArrayList<TextView>();
        viewList.add(tv_appoint);
        viewList.add(tv_reportform);
        viewList.add(tv_consumer);
        viewList.add(tv_modifypwd);
        viewList.add(tv_logout);
        tv_appoint.setOnClickListener(this);
        tv_reportform.setOnClickListener(this);
        tv_consumer.setOnClickListener(this);
        tv_modifypwd.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {

        changeSelectItem(0);
    }

    private void changeSelectItem(int position) {
            for (int i = 0; i <viewList.size() ; i++) {
                if (i == position){
                    viewList.get(i).setBackgroundColor(UIUtils.getColor(R.color.green));
                }else{
                    viewList.get(i).setBackgroundColor(UIUtils.getColor(R.color.text_black));
                }
            }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_appoint:
                changeSelectItem(0);
                switchMenuPager(0);
                currentClickMenuItem = 0;
                break;

            case R.id.tv_reportform:
                changeSelectItem(1);
                switchMenuPager(1);
                currentClickMenuItem = 1;
                break;

            case R.id.tv_consumer:
                changeSelectItem(2);
                switchMenuPager(2);
                currentClickMenuItem = 2;
                break;

            case R.id.tv_modifypwd:
//                changeSelectItem(3);
//                currentClickMenuItem = 3;
                break;

            case R.id.tv_logout:
                DialogUtils.showHintDialogTwo(mActivity, "确定", "取消", "提示", "是否要注销登录", new DialogUtils.OnDialogListener() {
                    @Override
                    public void onOk(View v) {
                        AccountManager.getInstance().logout();
                    }

                    @Override
                    public void onCancel(View v) {

                    }
                });
                break;

        }
    }




    public void switchMenuPager(int position) {
        MainUI mainUI = (MainUI) mActivity;
        // 获取到新闻中心界面，让它去更新相对应的菜单内容
//        mainUI.setContentFragment(position);
        mainUI.switchContent(FragmentFactory.createFragment(currentClickMenuItem), FragmentFactory.createFragment(position));
        //缩回左侧侧拉条目
        mainUI.slidingMenu.toggle();
        mainUI.setPicChage(position);
    }

}

