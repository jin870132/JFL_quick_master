package com.jinfulin.quick_master.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.UI.*;
import com.jinfulin.quick_master.UI.MainUI;
import com.jinfulin.quick_master.cache.CacheUtils;
import com.jinfulin.quick_master.util.GuideContoler;
import com.jinfulin.quick_master.util.UIUtils;

public class GuideUI extends Activity {
	private View pager_one;
	private View pager_two;
	private View pager_three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UIUtils.addActivity(this);
		setContentView(R.layout.activity_guide);
		openOtherActivity();
	}

	private void openOtherActivity() {

		boolean isFirstOpen = CacheUtils.getBoolean(CacheUtils.IS_APP_FIRST_OPEN, true);
		if (isFirstOpen) {
			initViewPager();
		} else {
			MainUI.actionStart(this);
			finish();
		}
	}


	/** 使用写好的库初始化引导页面 **/
	private void initViewPager() {
		GuideContoler contoler = new GuideContoler(this);

		LayoutInflater inflater = LayoutInflater.from(this);
		pager_one = inflater.inflate(R.layout.pager_one, null);
		pager_two = inflater.inflate(R.layout.pager_two, null);
		pager_three = inflater.inflate(R.layout.pager_three, null);
		contoler.init(pager_one, pager_two, pager_three);

		pager_three.findViewById(R.id.tv_login).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						CacheUtils.putBoolean(CacheUtils.IS_APP_FIRST_OPEN, false);
						com.jinfulin.quick_master.UI.GuideUI.this.startActivity(new Intent(com.jinfulin.quick_master.UI.GuideUI.this,LogingActivity.class));
						finish();
					}
				});



	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		UIUtils.removeActivity(this);
	}

}
