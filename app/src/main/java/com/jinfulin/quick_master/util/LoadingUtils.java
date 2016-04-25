package com.jinfulin.quick_master.util;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinfulin.quick_master.R;


public abstract class LoadingUtils {

	private Activity context;
	private RelativeLayout rl_error_state;
	private TextView tv_state;
	private ImageView iv_state_image;
	private TextView tv_state_back;
	private TextView tv_state_go;
	private RelativeLayout rl_loading;
	private RelativeLayout rl_empty_state;
	private TextView tv_empty;

	public LoadingUtils(Activity context) {
		super();
		this.context = context;
		 initview();
	}

	private void initview() {
			tv_state = (TextView) context.findViewById(R.id.tv_state);// 显示的成功失败状态文字详细描述
			iv_state_image = (ImageView) context.findViewById(R.id.iv_state_image);// 获取成功与否的图片
			tv_state_back = (TextView) context.findViewById(R.id.tv_state_back);// 返回按钮
			tv_state_go = (TextView) context.findViewById(R.id.tv_state_go);// 前进按钮
			rl_loading = (RelativeLayout) context.findViewById(R.id.rl_loading);
			rl_error_state = (RelativeLayout) context.findViewById(R.id.rl_error_state);
			rl_empty_state = (RelativeLayout) context.findViewById(R.id.rl_empty_state);
			tv_empty = (TextView) context.findViewById(R.id.tv_empty);
	}

	public void showLoadingView() {
		if (rl_loading!=null && rl_error_state!= null && rl_empty_state != null) {
			rl_loading.setVisibility(View.VISIBLE);
			rl_error_state.setVisibility(View.GONE);
			rl_empty_state.setVisibility(View.GONE);
		}
	};

	public void showSuccessView() {
		if (rl_loading!=null)rl_loading.setVisibility(View.GONE);
		if (rl_error_state!=null)rl_error_state.setVisibility(View.GONE);
		if (rl_empty_state!=null)rl_empty_state.setVisibility(View.GONE);
	};
	public void showSuccessView(String jsonstr) {
		if (rl_loading!=null)rl_loading.setVisibility(View.GONE);
		if (rl_error_state!=null)rl_error_state.setVisibility(View.GONE);
		if (rl_empty_state!=null)rl_empty_state.setVisibility(View.GONE);
		if (TextUtils.isEmpty(jsonstr)){this.showEmptyView();}

	};

	public void showErrorView() {
		if (rl_loading!=null && rl_error_state!= null && rl_empty_state != null) {
			rl_loading.setVisibility(View.GONE);
			rl_error_state.setVisibility(View.VISIBLE);
			rl_empty_state.setVisibility(View.GONE);

			tv_state.setVisibility(View.VISIBLE);
			tv_state_go.setVisibility(View.VISIBLE);
			tv_state_back.setVisibility(View.VISIBLE);
			iv_state_image.setVisibility(View.VISIBLE);

			tv_state_go.setVisibility(View.VISIBLE);
			tv_state_back.setVisibility(View.VISIBLE);
			tv_state_go.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					LoadingUtils.this.right_click();
				}
			});
			tv_state_back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
					context.startActivity(wifiSettingsIntent);

				}
			});
			tv_state_go.setText("刷新");
			tv_state_back.setText("设置网络");
			tv_state.setText("网络连接失败,无法显示数据");
		}
	};

	public  void showEmptyView() {
		if (rl_loading!=null && rl_error_state!= null && rl_empty_state != null) {
			rl_loading.setVisibility(View.GONE);
			rl_error_state.setVisibility(View.GONE);
			rl_empty_state.setVisibility(View.VISIBLE);
		}
	};
	public void showEmptyView(String emptyText) {
		if (rl_loading!=null && rl_error_state!= null && rl_empty_state != null) {
			rl_loading.setVisibility(View.GONE);
			rl_error_state.setVisibility(View.GONE);
			rl_empty_state.setVisibility(View.VISIBLE);
			tv_empty.setText(emptyText);
		}
	};
	public abstract void  right_click();

}
