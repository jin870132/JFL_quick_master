package com.jinfulin.quick_master.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.gson.Gson;
import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.beans.ClubMemberInfo;
import com.jinfulin.quick_master.net.Callback;
import com.jinfulin.quick_master.net.NetWork;
import com.jinfulin.quick_master.util.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MyAutoCompleteTextView extends EditText {

    Context context = null;
    MyTextWatcher myTextWatcher = null;
    PopSelectListener popSelectListener = null;
    private ClubMemberInfo clubMemberInfo;
    private String queryType;
    private boolean IsSetText = false;

    public MyAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        setPopw();
        this.addTextChangedListener(watcher);
    }

    /**
     * 设置要将试图加进去的父布局
     *
     * @param layout 只能是Relative父布局，如果是linear请用另一个方法
     */
    public void setFatherRelativeLayouyt(RelativeLayout layout) {
        this.relativeLayout = layout;
        isRLayout = true;
    }

    public void setFatherRelativeLayouyt(String queryType) {
        this.queryType = queryType;
        isRLayout = true;
    }

    /**
     * 设置要将试图加进去的父布局
     *
     * @param layout 只能是Linear父布局，如果是Relative请用另一个方法
     */
    public void setFatherLinearLayout(LinearLayout layout) {
        this.linearLayout = layout;
        isRLayout = false;
    }

    /**
     * 设置下拉内容的文字库
     *
     * @param list
     */
    public void setMemoryData(ArrayList<String> list) {
        this.memoryData = list;
    }

    /**
     * 如果要对此输入框添加TextWatch监听，请使用此方法，不要用系统的
     *
     * @param myTextWatcher
     */
    public void addMyTextWatcher(MyTextWatcher myTextWatcher) {
        this.myTextWatcher = myTextWatcher;
    }

    public void addpopSelectListener(PopSelectListener popSelectListener) {
        this.popSelectListener = popSelectListener;
    }

    /**
     * 手动隐藏掉这个下拉提示
     */
    public void removeTheShowView() {
        if (popView.isShown()) {
            if (isRLayout) {
                relativeLayout.removeView(popView);
            } else {
                linearLayout.removeView(popView);
            }
        }
    }


    TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!IsSetText) {
                queryClubMemberList(s.toString());
            }
            IsSetText = false;
            if (myTextWatcher != null) {
                myTextWatcher.onTextChanged(s, start, before, count);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            if (myTextWatcher != null) {
                myTextWatcher.beforeTextChanged(s, start, count, after);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                removeTheShowView();
            }
            if (myTextWatcher != null) {
                myTextWatcher.afterTextChanged(s);
            }
        }
    };

    ArrayList<String> memoryData = null;
    LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    private View popView = null;
    private ListView mlistView = null;
    private ArrayList<String> mList = null;
    private ArrayAdapter<String> mAdapter = null;//popw的listview的适配器
    RelativeLayout relativeLayout = null;
    LinearLayout linearLayout = null;
    private boolean isRLayout = false;

    private void setPopw() {
        if (this.popView == null) {
            popView = View.inflate(context, R.layout.popview, null);
        }

        if (mlistView == null) {
            mlistView = (ListView) popView.findViewById(R.id.pop_listview);
            mlistView.setItemsCanFocus(true);
            mlistView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    IsSetText = true;
                    if (popSelectListener != null) {
                        popSelectListener.OnPopSelect(clubMemberInfo, position);
                    }
//					MyAutoCompleteTextView.this.setText(mList.get(position));
                    if (isRLayout) {
                        relativeLayout.removeView(popView);
                    } else {
                        linearLayout.removeView(popView);
                    }
                }
            });
        }
        mList = new ArrayList<String>();
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(context, R.layout.list_item, R.id.txt_item, mList);
        }
        mlistView.setAdapter(mAdapter);
    }


    /**
     * 因为控件内部已经做了此系统接口的实现监听，这个接口是自己做的留给外部调用的
     * <p>触发机制、字段都和系统自带的一样,就不赘述了
     *
     * @author hz
     */
    public interface MyTextWatcher {
        /**
         * @param s
         * @param start
         * @param before
         * @param count
         */
        public void onTextChanged(CharSequence s, int start, int before, int count);

        public void beforeTextChanged(CharSequence s, int start, int count, int after);

        public void afterTextChanged(Editable s);
    }

    public interface PopSelectListener {
        public void OnPopSelect(ClubMemberInfo clubMemberInfo, int position);
    }


    private void queryClubMemberList(String s) {
        NetWork.getInstance().queryClubMemberList(s, "card", new Callback() {
            @Override
            public void success(String result) {

            }

            @Override
            public void notice(JSONObject jsonObject, String message) {//因为get请求的结果没有拼code结构的json

                Gson gson = new Gson();
                clubMemberInfo = gson.fromJson(jsonObject.toString(), ClubMemberInfo.class);
                ArrayList<String> list = new ArrayList<String>();
                for (int i = 0; i < clubMemberInfo.rows.size(); i++) {
                    list.add(clubMemberInfo.rows.get(i).card + "--" + clubMemberInfo.rows.get(i).name);
                }
                ShowPop(list);
            }

            @Override
            public void failed(String e) {

            }
        });

    }

    private void ShowPop(ArrayList list) {
        mList.clear();
        mList.addAll(list);
        if (mList.size() > 0) {
            mAdapter.notifyDataSetInvalidated();
            if (!popView.isShown()) {
                int[] top = new int[2];
                this.getLocationInWindow(top);
                //显示位置稍有不和，可自行修改，这里我就偷懒了
                layoutParams.topMargin = top[1] + UIUtils.dip2px(40);
                layoutParams.leftMargin = top[0];
                if (isRLayout) {

                    relativeLayout.addView(popView, layoutParams);
                } else {
                    linearLayout.addView(popView, layoutParams);
                }
                popView.setFocusable(true);
            }
        } else {
            if (isRLayout) {
                relativeLayout.removeView(popView);
            } else {
                linearLayout.removeView(popView);
            }
        }

    }
}
