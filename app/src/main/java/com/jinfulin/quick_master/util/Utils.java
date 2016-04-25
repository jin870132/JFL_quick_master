package com.jinfulin.quick_master.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by king on 2015/12/1.
 */
public class Utils {

    public static void toggleSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return com.jinfulin.quick_master.util.UIUtils.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 屏幕的高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return UIUtils.getContext().getResources().getDisplayMetrics().heightPixels;
    }
    public static String getDate(int daynum) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, daynum);//把日期往后增加n天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;

    }


}
