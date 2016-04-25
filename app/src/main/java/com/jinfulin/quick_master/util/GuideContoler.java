package com.jinfulin.quick_master.util;


import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.util.*;
import com.jinfulin.quick_master.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideContoler {
	private Context mContext;
	private ViewPager mViewPager;
	/**ViewPager要显示的视图集合**/
	private List<View> mViews;
	private GuideViewPagerAdapter mPagerAdapter;
	/**装指示器的LinearLayout**/
	private LinearLayout mIndicatorGroup;
	/**指示器的集合**/
	private View[] indicators;
	/**当指示器选择矩形时，默认的宽**/
	private static final int INDICATOR_WIDTH_FOR_RECT = 40;
	/**当指示器选择矩形时，默认的高**/
	private static final int INDICATOR_HEIGHT_FOR_RECT = 5;
	/**当指示器选择圆形时，默认的宽**/
	private static  int INDICATOR_WIDTH_FOR_OVAL = 0;
	/**当指示器选择圆形时，默认的高**/
	private static  int INDICATOR_HEIGHT_FOR_OVAL = 0;
	
	/**指示器的宽**/
	private int mIndicatorWidth ;
	/**指示器的高**/
	private int mIndicatorHeight;
	
	private int mIndicatorBgResForSelected;
	private int mIndicatorBgResForUnselected;
	/**指示器默认为圆形**/
	private ShapeType mShapeType;
	private int mpage;
	/**
	 * @param context
	 */
	public GuideContoler(Context context) {
		super();
		this.mContext = context;
		
	}
	
	private View view;//用于显示动画
	/***
	 * 设置数据,适用于前面页面是图片，最后一个页面是一个layout布局
	 * @param imgIds 图片的id数组
	 * @param view
	 */
	public void init(int[] imgIds,View view){
		mViews = new ArrayList<View>();
		for (int i = 0; i < imgIds.length; i++) {
			ImageView iv = new ImageView(mContext);
			iv.setImageResource(imgIds[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			mViews.add(iv);
		}
		mViews.add(view);
		set();
	}
	
	/***
	 * 设置数据,3个layout布局
	 * 
	 * @param view1,view2,view3 一共3个布局
	 */
	public void init(View view1,View view2,View view3){
		mViews = new ArrayList<View>();
		mViews.add(view1);
		mViews.add(view2);
		mViews.add(view3);
		set();
	}
	/**传入数据,当页面视图全是由layout生产的时候适用**/
	public void init(List<View> views){
		mViews = views;
		set();
	}
	
	/**设置ViewPager和指示器**/
	private void set(){
		//设置圆形的高
		/**当指示器选择圆形时，默认的宽**/
		 INDICATOR_WIDTH_FOR_OVAL = UIUtils.dip2px(8);
		/**当指示器选择圆形时，默认的高**/
		 INDICATOR_HEIGHT_FOR_OVAL = UIUtils.dip2px(8);
		setViewPager();
		setIndicators();
	}
	/**设置ViewPager**/
	private void setViewPager() {
		mViewPager = (ViewPager) ((Activity)mContext).findViewById(R.id.viewPager_lib);
		mViewPager.setOffscreenPageLimit(0);
		mPagerAdapter = new GuideViewPagerAdapter(mViews);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(pageChangeListener);
	}
	/**设置指示器**/
	private void setIndicators() {
		setConfigure(mShapeType);
		mIndicatorGroup = (LinearLayout) ((Activity)mContext).findViewById(R.id.indicatorGroup_lib);
		indicators = new View[mViews.size()];
		LayoutParams params = new LayoutParams(mIndicatorWidth, mIndicatorHeight);
		params.setMargins(0, 0, 15, 0);
		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new View(mContext);
			if(i == 0){
				indicators[i].setBackgroundResource(mIndicatorBgResForSelected);
			}else{
				indicators[i].setBackgroundResource(mIndicatorBgResForUnselected);
			}
			indicators[i].setLayoutParams(params);
			mIndicatorGroup.addView(indicators[i]);
		}
	}
	/**根据枚举参数，设置指示器的背景和宽高**/
	private void setConfigure(ShapeType shapeType) {
		if(shapeType != null){
			if(shapeType == ShapeType.OVAL){
				mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_OVAL : mIndicatorWidth;
				mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_OVAL : mIndicatorHeight;
				mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_oval;
				mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_oval;
			}else if(shapeType == ShapeType.RECT){
				mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_RECT : mIndicatorWidth;
				mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_RECT : mIndicatorHeight;
				mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_rect;
				mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_rect;
			}
		}else{
			mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_OVAL : mIndicatorWidth;
			mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_OVAL : mIndicatorHeight;
			mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_oval;
			mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_oval;
		}
	}
	/**监听ViewPager的页面变化**/
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		
		private int _position;

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < indicators.length; i++) {
				if(i == arg0){
					indicators[i].setBackgroundResource(mIndicatorBgResForSelected);
					
				}else{
					indicators[i].setBackgroundResource(mIndicatorBgResForUnselected);
				}
			}
//			call(arg0);
//			_position = arg0;
			
			

		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
//			if (arg0 == 2) {
////				call(_position);
//			}
		}
	};


	/***
	 * 定义一个枚举(用来指定指示器的形状)
	 * @author zhy
	 */
	public enum ShapeType{
		RECT,OVAL
	}
	
	public void setmIndicatorWidth(int mIndicatorWidth) {
		this.mIndicatorWidth = mIndicatorWidth;
	}
	public void setmIndicatorHeight(int mIndicatorHeight) {
		this.mIndicatorHeight = mIndicatorHeight;
	}
	public void setmShapeType(ShapeType mShapeType) {
		this.mShapeType = mShapeType;
	}
	



}


