package com.jinfulin.quick_master.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfulin.quick_master.R;


/**
 * 通用页面模板1：含上栏，包括左右两个按钮，一个title文字区
 *
 * @author zhe.yangz
 */
public class BaseActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
    }

    /**
     * 设置主内容区域的layoutRes
     *
     * @param layoutResId
     */
    public void baseSetContentView(int layoutResId) {
        FrameLayout fl_content = (FrameLayout) findViewById(R.id.fl_content);
        setImageLeft(R.mipmap.icon_back);
        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layoutResId, null);
        fl_content.addView(v);
    }





    //------------设置title信息---------------------

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
        return (ImageView) findViewById(R.id.iv_left);
    }
    public ImageView getImageRight() {
        return (ImageView) findViewById(R.id.iv_right);
    }
    public TextView getTextRight() {
        return (TextView) findViewById(R.id.tv_right);
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
    public void setImageRight() {
    }
        public boolean setBarTitleText(String titleText) {
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        if (null != tv_title) {
            tv_title.setText(titleText);
            return true;
        }
        return false;
    }
}
