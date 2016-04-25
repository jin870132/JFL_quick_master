
package com.jinfulin.quick_master.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.adapter.AdapterDialogSelectTxt;

import java.util.List;

public class DialogUtils {


    public static interface OnDialogListener {
        public void onOk(View v);

        public void onCancel(View v);
    }


    public static interface OnItemSelectListener {
        public void onItemSelected(AdapterDialogSelectTxt.Item item);
    }


    private static void showDialog(Dialog dialog) {
        try {
            if (dialog != null && !dialog.isShowing())
                dialog.show();
        } catch (Exception e) {
        }
    }


    public static void showHintDialogOne(Context context, String textBtnOk, String textTitle,
                                         String textHint, final OnDialogListener listener) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_hint_btn_ok, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView textContent = (TextView) view.findViewById(R.id.dialog_text_content);
        Button btnOk = (Button) view.findViewById(R.id.dialog_btn_ok);
        btnOk.setText(textBtnOk);
        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onOk(v);
                }
            }
        });
        title.setText(textTitle);
        textContent.setText(textHint);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = Utils.getScreenWidth() * 7 / 8;
        dialogWindow.setAttributes(lp);
        DialogUtils.showDialog(dialog);
    }


    public static void showHintDialogTwo(Context context, String textBtnOk, String textBtnCancel,
                                         String textTitle, String textHint, final OnDialogListener listener) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_hint_btn_two, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView textContent = (TextView) view.findViewById(R.id.dialog_text_content);
        Button btnOk = (Button) view.findViewById(R.id.dialog_btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.dialog_btn_cancel);
        btnOk.setText(textBtnOk);
        btnCancel.setText(textBtnCancel);


        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onOk(v);
                }
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onCancel(v);
                }
            }
        });
        title.setText(textTitle);
        textContent.setText(textHint);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = Utils.getScreenWidth() * 7 / 8;
        dialogWindow.setAttributes(lp);
        DialogUtils.showDialog(dialog);
    }


    public static void showSelectDialog(Context context, String title, List<AdapterDialogSelectTxt.Item> list,
                                        final OnItemSelectListener itemClickListener) {
        final Dialog selectDialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select, null);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setText(title);
        view.findViewById(R.id.dialog_btn_cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectDialog != null && selectDialog.isShowing()) {
                    selectDialog.dismiss();
                }
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.listview);
        final AdapterDialogSelectTxt adapter = new AdapterDialogSelectTxt(context, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterDialogSelectTxt.Item item = (AdapterDialogSelectTxt.Item) adapter.getItem(position);
                if (itemClickListener != null) {
                    itemClickListener.onItemSelected(item);
                }
                if (selectDialog != null && selectDialog.isShowing()) {
                    selectDialog.dismiss();
                }
            }
        });

        selectDialog.setContentView(view);
        Window dialogWindow = selectDialog.getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = Utils.getScreenWidth() * 7 / 8;
        dialogWindow.setAttributes(lp);
        DialogUtils.showDialog(selectDialog);
    }




}
