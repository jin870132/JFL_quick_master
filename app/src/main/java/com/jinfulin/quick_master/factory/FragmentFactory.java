package com.jinfulin.quick_master.factory;


import com.jinfulin.quick_master.base.BaseFragment;
import com.jinfulin.quick_master.fragment.OneFragment;
import com.jinfulin.quick_master.fragment.ThreeFragment;
import com.jinfulin.quick_master.fragment.TwoFragment;

import java.util.HashMap;

public class FragmentFactory {
	private static HashMap<Integer,BaseFragment> hashMap = new HashMap<Integer, BaseFragment>();
	public static BaseFragment createFragment(int position) {
		//根据传递进来的索引去生成Fragment(内存缓存获取,直接new)
		BaseFragment fragment = hashMap.get(position);
		if(fragment!=null){
			return fragment;
		}else{
			switch (position) {
			case 0:
				//创建首页对应的Fragment对象
				//每一个Fragment都是在onCreatView方法中返回界面view效果
				fragment = new OneFragment();
				break;
			case 1:
				fragment = new TwoFragment();
				break;
			case 2:
				fragment = new ThreeFragment();
				break;
			}
			//生成了fragment对象后,需要将其加入内存中hashMap
			hashMap.put(position, fragment);
			return fragment;
		}
	}

	private static BaseFragment getFragment(int position){
		return hashMap.get(position);
	}

	public static void clearnMap(){
		hashMap = new HashMap<Integer, BaseFragment>();
	}
}
